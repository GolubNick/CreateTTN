package ExcelHelper;

import Model.ExpressInvoice;
import Model.ExpressInvoiceProperties;
import NovaPoshta.RESTClientHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.util.Iterator;

public class ExcelHelper {

    private String path;
    private HSSFWorkbook myExcelBook;
    private HSSFSheet myExcelSheet;
    private HSSFRow row;

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

        Iterator<Row> it = myExcelSheet.iterator();
        it.next();
        while (it.hasNext()) {
            Row row = it.next();

            String RecipientName = row.getCell(3).getStringCellValue().replace("-", "").trim();
            String RecipientsPhone = row.getCell(4).getStringCellValue();
            String RecipientCityName = row.getCell(12).getStringCellValue().replaceAll(".*-(.*),\\sÂ³ää³ëåííÿ.*", "$1").trim();
            String RecipientAddressName = row.getCell(12).getStringCellValue().replaceAll(".*Â³ää³ëåííÿ\\s¹(\\d*).*", "$1").trim();
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

        }
        myExcelBook.write();
        myExcelBook.close();
    }
}
