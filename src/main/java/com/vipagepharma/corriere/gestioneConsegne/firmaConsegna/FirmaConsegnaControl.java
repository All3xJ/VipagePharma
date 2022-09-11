package com.vipagepharma.corriere.gestioneConsegne.firmaConsegna;

import com.vipagepharma.corriere.App;
import com.vipagepharma.corriere.entity.Ordine;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.vipagepharma.corriere.gestioneConsegne.visualizzaConsegne.VisualizzaConsegneControl;
import javafx.application.Platform;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FirmaConsegnaControl {

    public static FirmaConsegnaControl firmConsCtrlRef;

    private Ordine ordine;

    public FirmaConsegnaControl(){
        firmConsCtrlRef = this;
    }

    public void start(Ordine ordine) throws IOException {
        SchermataRiepilogoOrdine.ordine=ordine;
        App.setRoot("gestioneConsegne/firmaConsegna/SchermataRiepilogoOrdine");
    }

    public void premutoFirma(Ordine ordine) throws DocumentException, IOException {



        String[] ciao = new String[2];


        new Thread(""){
            public void run(){
                PannelloFirma.main(ciao);
            }
        }.start();

        this.ordine=ordine;
    }

    public void premutoOk() throws IOException {
        VisualizzaConsegneControl.visualConCtrlRef.rimuoviOrdineFirmato(ordine.idPrenotazione.get());
        App.setRoot("gestioneConsegne/visualizzaConsegne/SchermataConsegneOdierne");
        App.popup_stage.close();
        this.ordine=null;
    }

    public void premutoConferma() throws DocumentException, IOException {
        this.creaPDF(this.ordine);
        Platform.runLater(new Runnable() {
            @Override public void run() {
                try {
                    App.newWind("gestioneConsegne/firmaConsegna/AvvisoOperazioneRiuscita");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void creaPDF(Ordine ordine) throws IOException, DocumentException {
        Document document = new Document(PageSize.A5, 20, 20, 20, 20);
        String foo = String.valueOf(ordine.idPrenotazione.get());
        PdfWriter.getInstance(document, new FileOutputStream("/tmp/ricevuta_" + foo + ".pdf"));
        document.open();
        this.aggiungiMetadati(document);
        this.aggiungiTitolo(document, ordine);
        this.aggiungiFirma(document);
        document.close();
        //File file = new File("/tmp/ricevuta_" + foo + ".pdf");
        //byte[] fileContent = Files.readAllBytes(file.toPath());
        ordine.setFilePDF("/tmp/ricevuta_" + foo + ".pdf");

    }

    private void aggiungiMetadati(Document document) {
        document.addTitle("Ricevuta di Consegna");
    }

    private void aggiungiTitolo(Document d, Ordine o) throws DocumentException, IOException {
        Paragraph titolo = new Paragraph();
        Image img = Image.getInstance("src/main/java/com/vipagepharma/corriere/hq_per_pdf.png");
        img.scalePercent(60,60);
        img.setAlignment(Element.ALIGN_CENTER);
        titolo.add(img);
        Font fontTitolo = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.BLACK);
        Paragraph p1 = (new Paragraph("Ricevuta di consegna ordine\n\n", fontTitolo));
        p1.setAlignment(Element.ALIGN_CENTER);
        titolo.add(p1);
        Font fontSottotitolo = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("HH:mm");
        Paragraph p2 = new Paragraph("Questa ricevuta è stata generata in data " + myDateObj.format(myFormatObj)+" alle ore "+ myDateObj.format(myFormatObj2)+". La farmacia '"+o.nomeFarmaciaConsegna.get()+"' ha firmato l'avvenuta consegna dell'ordine n. " + String.valueOf(o.idPrenotazione.get()), fontSottotitolo);
        p2.setAlignment(Element.ALIGN_CENTER);
        titolo.add(p2);
        d.add(titolo);
        int i = 0;
        while (i < 2) {
            d.add(Chunk.NEWLINE);
            i++;
        }
    }


    private void aggiungiFirma(Document d) throws DocumentException, IOException {
        Paragraph titolo = new Paragraph();
        Font fontTitolo = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
        Paragraph p1 = (new Paragraph("Firma del farmacista:", fontTitolo));
        p1.setAlignment(Element.ALIGN_CENTER);
        titolo.add(p1);
        Image img = Image.getInstance("/tmp/foo.jpg");
        img.scalePercent(50,50);
        img.setAlignment(Element.ALIGN_CENTER);
        titolo.add(img);
        d.add(Chunk.NEWLINE);
        d.add(titolo);
    }

    public void premutoIndietro(String schermataPrecedente) throws IOException {
        App.setRoot(schermataPrecedente);
    }
    public void premutoHome() throws IOException {
        App.setRoot("SchermataPrincipale");
    }
    public void premutoLogout() throws IOException {
        App.setRoot("autenticazione/login/SchermataLogin");
    }
}
