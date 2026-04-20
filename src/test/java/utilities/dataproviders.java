package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class dataproviders {

    @DataProvider(name="LoginData")
    public String[][] getData() throws IOException {
        String path="C:\\Users\\2478501\\IdeaProjects\\FrameWorkDemo\\testData\\data.xlsx";
        ExcelUtils xlutils=new ExcelUtils(path);
        int numRow=xlutils.getRowCounts("Sheet1");
        int numCols=xlutils.getCellCount("Sheet1",1);
        String[][] data=new String[numRow][numCols];
        for(int i=1;i<=numRow;i++){
            for(int j=0;j<numCols;j++){
                data[i-1][j]=xlutils.getCellData("Sheet1",i,j);
            }
        }

        return data;
    }
}
