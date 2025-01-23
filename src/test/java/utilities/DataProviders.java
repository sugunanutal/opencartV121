package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][]getData() throws IOException
	{
		//String path = System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx";
		//String path=System.getProperty("C:\\Users\\Suguna Nutalapati\\Desktop\\projects\\opencartdemo\\Opencart_LoginData.xlsx");
		String path=".\\testData\\Opencart_LoginData.xlsx";// taking excel file from testData
		
		ExcelUtility xlutil= new ExcelUtility(path);//creating an object for XLUtility
		
		int totalrows= xlutil.getRowCount("Sheet1");
		int totalcols= xlutil.getCellCount("Sheet1", 1);//1 is the row number
		
		String logindata[][] = new String [totalrows][totalcols];//created for two dimension array which can store
		
		for (int i=1; i<=totalrows; i++)//1 //read the data from xl storing in two dimensional array
		{
			for(int j=0;j<totalcols;j++)//0 i is rows and j is col
			{
				
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);  //1,0 //array index starts from 0, that's why i-1
				
				
			}
		}
		
		return logindata; //returning two dimension array
		
		
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
	
}
