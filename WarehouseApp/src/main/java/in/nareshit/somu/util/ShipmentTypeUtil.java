package in.nareshit.somu.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class ShipmentTypeUtil {
	public void generatePieChart(String path,List<Object[]> list) {
		//1 convert List<Object> data into DataSet
		DefaultPieDataset dataset=new DefaultPieDataset();
		for(Object[] ob:list) {
			dataset.setValue(
					ob[0].toString(),  		//key-String
					Double.valueOf(ob[1].toString()));  //value-Double
		}
		
		//2 convert DataSet into JFreeChart Object
		JFreeChart chart=ChartFactory.createPieChart("SHIPMENT MODES", dataset);
		//JFreeChart chart=ChartFactory.createPieChart3D("SHIPMENT MODES", dataset);
		
		//3 convert JFreeChart Object into image format(png/jpg) using ChartUtils
		try {
			ChartUtils.saveChartAsPNG(new File(path+"/shipmentA.png"), chart, 600, 400);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void generateBarChart(String path,List<Object[]> list) {
		//generate DataSet from List<Object[]>
		DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		for(Object[] ob : list) {
			dataset.setValue(Double.valueOf(ob[1].toString()), ob[0].toString(), "");
		}
		
		
		//generate JFreeChart obj using ChartFactory
		JFreeChart chart=ChartFactory.createBarChart("SHIPMENT MODE","MODES", "COUNT", dataset);
		
		//convert to image jpg/png format using ChartUtils 
		try {
			ChartUtils.saveChartAsPNG(new File(path+"/shipmentB.png"), chart, 600, 600);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
