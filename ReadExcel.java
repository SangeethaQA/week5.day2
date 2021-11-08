package week5.day2.assignment2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static  String[][] readData(String excelfilename) throws IOException {
		// TODO Auto-generated method stub
		XSSFWorkbook wb= new XSSFWorkbook("./data/"+excelfilename+".xlsx");
		XSSFSheet ws= wb.getSheet("Sheet1");
		int rowCount = ws.getLastRowNum();
		int cellCount= ws.getRow(0).getLastCellNum();
		String [][] data= new String [rowCount][cellCount];
		
		for (int i=1;i<=rowCount ;i++)
		{
			for (int j=0;j< cellCount;j++)
			{
				String text= ws.getRow(i).getCell(j).getStringCellValue();
				System.out.println(text);
				data[i-1][j]=text;
				
			}
		
		}
		wb.close();
		return data;
	
	}
	
	

}
