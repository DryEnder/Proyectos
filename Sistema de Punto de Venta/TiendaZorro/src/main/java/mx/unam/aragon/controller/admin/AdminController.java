package mx.unam.aragon.controller.admin;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.IdInventario;
import mx.unam.aragon.model.entity.InventarioEntity;
import mx.unam.aragon.service.*;
import mx.unam.aragon.service.SMTP.EmailSenderService;
import mx.unam.aragon.service.XLS.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    InventarioService inventarioService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ExcelService excelService;
    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private AlmacenService almacenService;

    @Autowired
    EmailSenderService emailSenderService;

    @GetMapping("")
    public String adminIndex() {
        return "admin/admin-page";
    }

    @GetMapping("/")
    public String adminRedirect() {
        return "redirect:/admin";
    }

    @GetMapping("/inventario")
    public String inventario(Model model) {
        model.addAttribute("inventario", inventarioService.findAll());
        model.addAttribute("contenido", "Consultando Inventario");
        return "admin/inventario";
    }

    @GetMapping("/inventario/{ida}")
    public String inventarioArtCorreo(@PathVariable Long ida, Model model) {
        InventarioEntity inventarioEncontrado = inventarioService.findById(new IdInventario(articuloService.findById(ida),almacenService.findById(1L)));
        System.out.println(inventarioEncontrado);
        model.addAttribute("item", inventarioEncontrado);
        model.addAttribute("contenido", "Solicitar articulo");
        return "admin/invArt";
    }

    @PostMapping("/inventario/correo")
    public String guardarActor(@Valid @ModelAttribute(value = "item") InventarioEntity item, BindingResult result, Model model) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "admin/invArt";
        }
        //realizar la lògica de negocio
        //TODO quitar comentario de esto
        System.out.println(item.getIdInventario().getArticulo().getNombre());
        System.out.println(item.getIdInventario().getArticulo().getProveedor().getNombre());
        System.out.println(item.getIdInventario().getArticulo().getProveedor().getEmail());
        System.out.println(item.getNuevoStock());
//
        String mailProveedor = item.getIdInventario().getArticulo().getProveedor().getEmail();
        String motivo = "Solicitud de artticulo";
        String nombreProveedor = item.getIdInventario().getArticulo().getProveedor().getNombre();
        String nombreArticulo = item.getIdInventario().getArticulo().getNombre();
        Integer cantidad = item.getNuevoStock();

        emailSenderService.correoProveedor(mailProveedor,motivo,nombreProveedor,nombreArticulo,cantidad);
//
        model.addAttribute("contenido", "Se almaceno con exito");
        return "redirect:/admin/inventario?ok";
    }

    //se abrogan las funcionalidades de cliente.
//    @GetMapping("/cliente")
//    public String cliente(Model model) {
//        model.addAttribute("clientes", clienteService.findAll());
//        model.addAttribute("contenido", "Consulta de cliente");
//        return "admin/cliente";
//    }

    @GetMapping("/inventario/generarexcel")
    public ResponseEntity<byte[]> generaExcel() throws IOException {
        List<InventarioEntity> inventarios = inventarioService.findAll();
        byte[] excelBytes = excelService.generarExcel(inventarios);
        LocalDate fecha = LocalDate.now();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=inventario " + fecha + ".xlsx")
                .body(excelBytes);
    }

}