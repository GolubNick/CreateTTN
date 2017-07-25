package ExcelHelper;

import Model.ExpressInvoice;
import Model.ExpressInvoiceProperties;
import NovaPoshta.RESTClientHelper;
import UI.UserInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

public class ExcelHelper {

    private HSSFWorkbook myExcelBook;
    private HSSFSheet myExcelSheet;

    public ExcelHelper(String pathFile) {
        try {
            getTTNAndWriteIT(pathFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTTNAndWriteIT(String pathFile) throws Exception {
        myExcelBook = new HSSFWorkbook(new FileInputStream(pathFile));
        myExcelSheet = myExcelBook.getSheetAt(0);
        int countRows = 100 / myExcelSheet.getLastRowNum();
        Iterator<Row> it = myExcelSheet.iterator();
        if (it.hasNext()) {
            it.next();
        }
        while (it.hasNext()) {
            UserInterface.progressBar.setValue(countRows);
            Row row = it.next();

            String RecipientName = row.getCell(3).getStringCellValue().replace("-", "").trim();
            String RecipientsPhone = row.getCell(4).getStringCellValue();
            String RecipientCityName = row.getCell(12).getStringCellValue().replaceAll(".*-\\s(\\D*?),.*", "$1").trim();
            String RecipientAddressName = row.getCell(12).getStringCellValue().contains("№") ? row.getCell(12).getStringCellValue().replaceAll(".*№(\\d*).*", "$1").trim() : "";
            String price = String.valueOf(row.getCell(6).getNumericCellValue());

            ExpressInvoiceProperties expressInvoiceProperties = new ExpressInvoiceProperties();
            expressInvoiceProperties.setCost(price);
            expressInvoiceProperties.setDescription("test");
            expressInvoiceProperties.setRecipientAddressName(RecipientAddressName);
            expressInvoiceProperties.setRecipientCityName(RecipientCityName);
            expressInvoiceProperties.setRecipientName(RecipientName);
            expressInvoiceProperties.setRecipientsPhone(RecipientsPhone);
            ExpressInvoice expressInvoice = new ExpressInvoice(expressInvoiceProperties);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(expressInvoice);

            String ttn = new RESTClientHelper().getTTN(json);

            row.getCell(14).setCellValue(ttn);
            countRows+=countRows;
        }
        UserInterface.progressBar.setValue(100);
        myExcelBook.write(new FileOutputStream(pathFile));
        myExcelBook.close();
    }
}
