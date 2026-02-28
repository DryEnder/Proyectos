package mx.unam.aragon.controller.cajero;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.*;
import mx.unam.aragon.service.*;
import mx.unam.aragon.service.PDF.PDFSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("cajero")
@PreAuthorize("hasRole('CAJERO')")
public class CajeroController {
    @Autowired
    ClienteService clienteService;

    @Autowired
    VentaService ventaService;

    @Autowired
    DetalleVentaService detalleVentaService;
    @Autowired
    private InventarioService inventarioService;
    @Autowired
    private AlmacenService almacenService;
    //    @Autowired
//    private UsuarioService usuarioService;
    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private PDFSenderService pdfSenderService;





    @GetMapping("")
    public String cajero(Model model, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = auth.getName();
        ClienteEntity cliente = clienteService.findById(1L);
        VentaEntity venta = VentaEntity.builder()
                .cliente(cliente)
                .almacen(almacenService.findById(1L))
                //.usuario(usuario.getUsuario())
                .fechaHora(LocalDateTime.now())
                .numComprobante("V" + cliente.getTelefono())
                .completado(false)
                .build();
        ventaService.save(venta);
        return "redirect:/cajero/venta?ventaId=" + venta.getId();
    }
    @GetMapping("/")
    public String cajeroR(Model model) {

        return "redirect:/cajero";
    }

    @GetMapping(value = "crear-cliente/{id}")
    public String crearClienteForm(Model model, @PathVariable("id") Long ventaId) {
        VentaEntity venta = ventaService.findById(ventaId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = auth.getName();

        model.addAttribute("contenido", "Crear cliente nuevo");
        ClienteEntity cliente = new ClienteEntity(); // Formulario vacío
        model.addAttribute("nombre", nombreUsuario);
        model.addAttribute("cliente", cliente);
        model.addAttribute("ventaa", venta.getId());

        return "cajero/crear-cliente";
    }

    //@GetMapping("/busqueda")
    //public String busqueda(Model model, @ModelAttribute("venta") VentaEntity ventaZ,  @ModelAttribute("listo") String listo) {
    //    VentaEntity venta = ventaService.findById(ventaZ.getId());
    //    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //    String nombreUsuario = auth.getName();
    //    model.addAttribute("contenido", "Busqueda de cliente");
    //    model.addAttribute("cliente", new ClienteEntity());
    //    model.addAttribute("listo", listo);
    //    model.addAttribute("nombre", nombreUsuario);
    //    model.addAttribute("venta", venta);
    //    return "cajero/cajero-page";
    //}

    @PostMapping("buscarTel")
    public String buscarTel(@ModelAttribute("cliente") ClienteEntity cliente, Model model, RedirectAttributes redirectAttributes) {
        ClienteEntity clienteEncontrado = clienteService.findByTelefono(cliente.getTelefono());
        return getString(redirectAttributes, clienteEncontrado);
    }

    @PostMapping("buscarCorreo")
    public String buscarCorreo(@ModelAttribute("cliente") ClienteEntity cliente, Model model, RedirectAttributes redirectAttributes) {
        ClienteEntity clienteEncontrado = clienteService.findByEmail(cliente.getEmail());
        return getString(redirectAttributes, clienteEncontrado);
    }

    private String getString(RedirectAttributes redirectAttributes, ClienteEntity clienteEncontrado) {
        if (clienteEncontrado == null) {
            return "redirect:/cajero?no";
        }
        VentaEntity venta = VentaEntity.builder()
                .cliente(clienteEncontrado)
                .almacen(almacenService.findById(1L))
                //.usuario(usuario.getUsuario())
                .fechaHora(LocalDateTime.now())
                .numComprobante("V" + clienteEncontrado.getTelefono())
                .completado(false)
                .build();
        ventaService.save(venta);
        redirectAttributes.addAttribute("venta", venta.getId());
        return "redirect:/cajero/venta";
    }


    @GetMapping("venta")
    public String venta(@RequestParam("ventaId") Long ventaId, @ModelAttribute("error") String error, Model model, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = auth.getName();
        VentaEntity venta = ventaService.findById(ventaId);

        if (venta.getId() == null || venta.isCompletado()) {
            return "redirect:/cajero?nv";
        }

        // Asegurar cliente no nulo
        if (venta.getCliente() == null) {
            ClienteEntity clienteAnonimo = new ClienteEntity();
            clienteAnonimo.setNombre("Cliente anónimo");
            venta.setCliente(clienteAnonimo);
        }

        List<InventarioEntity> inventario = inventarioService.findAll();
        model.addAttribute("inventario", inventario);
        model.addAttribute("error", error);
        model.addAttribute("nombre", nombreUsuario);
        model.addAttribute("boton", true);
        List<DetalleVentaEntity> detalle = detalleVentaService.findByVentaId(venta.getId());
        float total = 0.0f;
        for (DetalleVentaEntity detalleVentaEntity : detalle) {
            total += detalleVentaEntity.getPrecio();
        }
        venta.setTotal(total);
        ventaService.save(venta);

        model.addAttribute("detalle", detalle);
        model.addAttribute("ventaa", venta);
        return "cajero/venta";
    }

    @GetMapping("buscar-cliente")
    public String buscarCliente(@RequestParam("busqueda") String busqueda, @RequestParam("ventaId") Long ventaId, RedirectAttributes redirectAttributes) {
        ClienteEntity cliente = clienteService.findByTelefonoOrCorreo(busqueda);
        if (cliente == null) {
            redirectAttributes.addFlashAttribute("error", "Cliente no encontrado");
            return "redirect:/cajero/venta?ventaId=" + ventaId;
        }

        VentaEntity venta = ventaService.findById(ventaId);
        if (venta == null || venta.isCompletado()) {
            return "redirect:/cajero?nv";
        }

        venta.setCliente(cliente);
        ventaService.save(venta);

        return "redirect:/cajero/venta?ventaId=" + ventaId;
    }

    @PostMapping("agregar-producto")
    public String agregarProductoAVenta(
            @ModelAttribute("venta") VentaEntity ventaZ,
            @RequestParam("idVenta") Long idVenta,
            @RequestParam("idArticulo") Long idArticulo,
            @RequestParam("cantidad") int cantidad,
            RedirectAttributes redirectAttributes
    ) {
        VentaEntity venta = ventaService.findById(idVenta);
        ArticuloEntity articulo = articuloService.findById(idArticulo);
        InventarioEntity inv = inventarioService.findById(new IdInventario(articulo,almacenService.findById(1L)));
        // Validación de stock disponible (opcional pero recomendable)
        int stockDisponible = inv.getStock();
        if (stockDisponible < cantidad) {
            redirectAttributes.addFlashAttribute("error", "Stock insuficiente para " + articulo.getNombre());
            redirectAttributes.addAttribute("ventaId", venta.getId());
            return "redirect:/cajero/venta";
        }

        DetalleVentaEntity detalleExistente = detalleVentaService.findByVentaAndArticulo(venta, articulo);

        if (detalleExistente != null) {
            // Actualiza cantidad y precio
            int nuevaCantidad = detalleExistente.getCantidad() + cantidad;
            detalleExistente.setCantidad(nuevaCantidad);
            detalleExistente.setPrecio(articulo.getPrecioVenta() * nuevaCantidad); // puedes recalcular si es necesario
            detalleVentaService.save(detalleExistente);
        } else {
            // Crear detalle_venta
            DetalleVentaEntity detalle = new DetalleVentaEntity();
            detalle.setVenta(venta);
            detalle.setArticulo(articulo);
            detalle.setCantidad(cantidad);
            detalle.setPrecio(articulo.getPrecioVenta() * cantidad);

            detalleVentaService.save(detalle);}

        // Actualizar stock (opcional aquí, o al finalizar venta)
        inv.setStock(inv.getStock() - cantidad);
        inventarioService.save(inv);


        redirectAttributes.addAttribute("ventaId", venta);
        return "redirect:/cajero/venta";
    }

    @PostMapping("eliminar-producto")
    public String eliminarProductoAVenta(@ModelAttribute("venta") VentaEntity ventaZ, @RequestParam("idVenta") Long idVenta,
                                         @RequestParam("detalle") Long idDetalle, RedirectAttributes redirectAttributes){
        DetalleVentaEntity detalleExistente = detalleVentaService.findById(idDetalle);
        VentaEntity venta = ventaService.findById(idVenta);
        InventarioEntity inv = inventarioService.findById(new IdInventario(detalleExistente.getArticulo(),almacenService.findById(1L)));
        inv.setStock(inv.getStock() + detalleExistente.getCantidad());
        inventarioService.save(inv);
        detalleVentaService.deleteById(idDetalle);
        redirectAttributes.addAttribute("ventaId", venta.getId());
        return "redirect:/cajero/venta";

    }

    @GetMapping(value = "guardar-transaccion/{id}")
    public String guardarTransaccion(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes) throws IOException, MessagingException {

        VentaEntity venta = ventaService.findById(id);
        List<DetalleVentaEntity> detalle = detalleVentaService.findByVentaId(id);

        // Validar si el correo del cliente es válido
        if (!esCorreoValido(venta.getCliente().getEmail())) {
            redirectAttributes.addFlashAttribute("error", "No existe cliente, favor de seleccionar o crear uno");
            redirectAttributes.addAttribute("ventaId", venta.getId());
            return "redirect:/cajero/venta?id=" + id;
        }

        // Guardar la venta
        venta.setCompletado(true);
        ventaService.save(venta);
        System.out.println("se guardó la transacción");
        System.out.println("generando pdf");

        // Generar y enviar el PDF
        ByteArrayInputStream bis = pdfSenderService.exportar(venta, detalle);
        pdfSenderService.sendMessageWithAttachment(bis, venta);

        return "redirect:/cajero?listo";
    }


//    @GetMapping(value = "guardar-transaccion/{id}")
//    public ResponseEntity<InputStreamResource> guardarTransaccion(
//            @PathVariable("id") Long id,Model model,RedirectAttributes redirectAttributes,
//            HttpServletResponse httpServletResponse) throws IOException, MessagingException {
//        VentaEntity venta = ventaService.findById(id);
//        List<DetalleVentaEntity> detalle = detalleVentaService.findByVentaId(id);
//        venta.setCompletado(true);
//        ventaService.save(venta);
//        System.out.println("se guardo la transaccion");
//        System.out.println("generando pddf");
//
//        ByteArrayInputStream bis = pdfSenderService.exportar(venta,detalle);
//
//        pdfSenderService.sendMessageWithAttachment(bis, venta);
//
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.add("Content-Disposition", "attachment;filename=reporte_"+venta.getNumComprobante()+ ".pdf");
//        headers.add("Redirect-Ref", "/cajero");
//
//        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(bis));
//        //return "redirect:/cajero";
//    }

    @GetMapping("destruir-transaccion/{id}")
    public String destruirVenta(
            @PathVariable("id") Long id,Model model,RedirectAttributes redirectAttributes
    ){

        VentaEntity venta = ventaService.findById(id);
        if(venta.isCompletado()) {
            return "redirect:/cajero?nv";
        }
        List<DetalleVentaEntity> detalle = detalleVentaService.findByVentaId(venta.getId());

        for(DetalleVentaEntity detalleVentaEntity : detalle) {
            InventarioEntity inv = inventarioService.findById(new IdInventario(detalleVentaEntity.getArticulo(),almacenService.findById(1L)));
            inv.setStock(inv.getStock() + detalleVentaEntity.getCantidad());
            inventarioService.save(inv);
            detalleVentaService.deleteById(detalleVentaEntity.getId());
        }
        ventaService.deleteById(id);

        return "redirect:/cajero";

    }

    @PostMapping("guardar-cliente")
    public String guardarCliente(@Valid @ModelAttribute("cliente") ClienteEntity cliente,
                                 @RequestParam("ventaId") Long ventaId, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("success","Revisa los campos ");
            model.addAttribute("venta", ventaService.findById(ventaId));
            return "cajero/guardar-cliente";
        }
        clienteService.save(cliente);

        VentaEntity venta = ventaService.findById(ventaId);
        venta.setCliente(cliente);
        ventaService.save(venta);

        redirectAttributes.addAttribute("ventaId", ventaId);
        model.addAttribute("cliente", cliente);
        model.addAttribute("ventaId", venta);
        return "redirect:/cajero/venta";
    }
    public boolean esCorreoValido(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return correo != null && correo.matches(regex);
    }

}