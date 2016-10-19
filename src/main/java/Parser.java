import java.io.*;
import java.util.Iterator;

import org.apache.poi.ddf.EscherColorRef;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import Utilities.Log;


public class Parser
{
    public static void main(String args[])throws Exception
    {
        final File folder = new File("./DataSource/");
        listFilesForFolder(folder);
    }

    public static void listFilesForFolder(final File folder)
    {
        try
        {
            Log.logger.info("folder : "+ folder);
            File[] listOfFiles = folder.listFiles();
            Log.logger.debug("Files : ");
            for (int i = 0; i < listOfFiles.length; i++)
            {
                File file = listOfFiles[i];
                Log.logger.debug(file.getName() + " Searched");
                if ((file.isFile() && file.getName().endsWith(".xls")) || (file.isFile() && file.getName().endsWith(".xlsx")))
                {
                    Log.logger.debug("Excel : "+file.getName());
                    ExcelParser(file);
//                    SampleExcelParser(file);
                }
                else
                {
                    Log.logger.debug("Not an Excel File");
                }
            }
        }
        catch (Exception e)
        {
            Log.logger.error("Exception : "+ e.getMessage());
        }
    }

    public static void ExcelParser(File file)
    {
        try
        {
            System.out.println("File : "+file.getName());
            InputStream inp = new FileInputStream(file.getAbsoluteFile());
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            ExcelExtractor extractor = new ExcelExtractor(wb);
            extractor.setFormulasNotResults(false);
            extractor.setIncludeSheetNames(true);
            String text = extractor.getText();
            System.out.println(text);
        }
        catch (Exception e)
        {
            Log.logger.error("Exception : "+ e.getMessage());
        }
    }
//    public static void SampleExcelParser(File file)
//    {
//        try
//        {
//            FileInputStream inputStream = new FileInputStream(file.getAbsoluteFile());
//            Workbook workbook = new XSSFWorkbook(inputStream);
//            Sheet firstSheet = workbook.getSheetAt(0);
//            Iterator<Row> iterator = firstSheet.iterator();
//            while(iterator.hasNext())
//            {
//                Row nextRow = iterator.next();
//                Iterator<Cell> cellIterator = nextRow.cellIterator();
//                while (cellIterator.hasNext())
//                {
//                    Cell cell = cellIterator.next();
//                    switch (cell.getCellType())
//                    {
//                        case Cell.CELL_TYPE_STRING:
//                            System.out.print(cell.getStringCellValue());
//                            break;
//                        case Cell.CELL_TYPE_BOOLEAN:
//                            System.out.print(cell.getBooleanCellValue());
//                            break;
//                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.print(cell.getNumericCellValue());
//                            break;
//                        case Cell.CELL_TYPE_BLANK:
//                            break;
//                    }
//                    System.out.print(" - ");
//                }
//                System.out.println();
//            }
//            workbook.close();
//            inputStream.close();
//        }
//        catch (Exception e)
//        {
//            Log.logger.error("Exception : "+e.getMessage());
//        }
//    }
}