package odessa.shop.ExcelHelper;

import odessa.shop.LogHelper.Logger;
import odessa.shop.Model.ExpressInvoice;
import odessa.shop.Model.ExpressInvoiceProperties;
import odessa.shop.NovaPoshta.RESTClientHelper;
import odessa.shop.UI.UserInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
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
            Logger.get().setWriter(e.getMessage());
            Logger.get().closeWriter();
        }
    }

    private void getTTNAndWriteIT(String pathFile) throws Exception {
        int count = 1;
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
            boolean value = row.getCell(14).getCellTypeEnum().compareTo(CellType.STRING) == 0 ? (row.getCell(14).getStringCellValue().isEmpty() ? true : false) : (row.getCell(14).getNumericCellValue() == 0.0 ? true : false);
            if (value) {
                String RecipientName = row.getCell(3).getStringCellValue().replace("-", "").trim();
                String RecipientsPhone = row.getCell(4).getStringCellValue();
                String RecipientCityName = row.getCell(12).getStringCellValue().replaceAll(".*-\\s(\\D*?),.*", "$1").trim();
                String RecipientAddressName = row.getCell(12).getStringCellValue().contains("№") ? row.getCell(12).getStringCellValue().replaceAll(".*№(\\d*).*", "$1").trim() : "1";
                String price = String.valueOf(row.getCell(6).getNumericCellValue());
                String description = row.getCell(20).getStringCellValue().replace("\n", " ").replace("+", " ");

                ExpressInvoiceProperties expressInvoiceProperties = new ExpressInvoiceProperties();
                expressInvoiceProperties.setCost(price);
                expressInvoiceProperties.setDescription(description);
                expressInvoiceProperties.setRecipientAddressName(RecipientAddressName);
                expressInvoiceProperties.setRecipientCityName(RecipientCityName);
                expressInvoiceProperties.setRecipientName(RecipientName);
                expressInvoiceProperties.setRecipientsPhone(RecipientsPhone);
                ExpressInvoice expressInvoice = new ExpressInvoice(expressInvoiceProperties);

                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(expressInvoice);

                String ttn = new RESTClientHelper().getTTN(json);
                if (ttn.isEmpty()) {
                    Logger.get().setWriter(count++ + " : " + RecipientName + " - " + RecipientsPhone + " - " +
                            RecipientCityName + " - " + RecipientAddressName + " - " + price + " - " + description);
                }
                row.getCell(14).setCellType(CellType.STRING);
                row.getCell(14).setCellValue(ttn);
            }
            countRows += countRows;
        }
        Logger.get().closeWriter();
        myExcelBook.write(new FileOutputStream(pathFile));
        myExcelBook.close();
    }
}
