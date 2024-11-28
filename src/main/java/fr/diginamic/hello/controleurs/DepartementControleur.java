package fr.diginamic.hello.controleurs;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fr.diginamic.hello.dtos.DepartementDto;
import fr.diginamic.hello.dtos.VilleDto;
import fr.diginamic.hello.exceptions.FunctionalException;
import fr.diginamic.hello.services.DepartementService;
import fr.diginamic.hello.services.VilleService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

    @Autowired
    private DepartementService departementService;

    @Autowired
    private VilleService villeService;

    @GetMapping
    public List<DepartementDto> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartementDto> getDepartementById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(departementService.getDepartementById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DepartementDto> addDepartement(@RequestBody DepartementDto departementDto) {
        return ResponseEntity.ok(departementService.addDepartement(departementDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable int id) {
        try {
            departementService.deleteDepartement(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{codeDepartement}/villes")
    public ResponseEntity<List<VilleDto>> getVillesByDepartement(@PathVariable String codeDepartement) {
        try {
            List<VilleDto> villes = villeService.getVillesByDepartementCode(codeDepartement);
            return ResponseEntity.ok(villes);
        } catch (FunctionalException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/export-pdf/{codeDepartement}")
    public void exportDepartementToPdf(@PathVariable String codeDepartement, HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=departement.pdf");

            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            String nomDepartement = departementService.getNomDepartementApi(codeDepartement);
            document.addTitle("Fiche Département");
            document.newPage();
            document.add(new Paragraph("Département : " + nomDepartement));
            document.add(new Paragraph("Code Département : " + codeDepartement));
            document.add(new Paragraph("\n"));
            List<VilleDto> villes = villeService.getVillesByDepartementCode(codeDepartement);

            PdfPTable table = new PdfPTable(2);
            table.addCell("Nom de la Ville");
            table.addCell("Population");

            for (VilleDto ville : villes) {
                table.addCell(ville.getNomVille());
                table.addCell(String.valueOf(ville.getNbHabitants()));
            }
            document.add(table);

            document.close();
            response.flushBuffer();

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'exportation PDF", e);
        }
    }
}
