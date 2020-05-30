package utils;

import org.apache.commons.io.FileDeleteStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class FileUtils {
	
	public static void deleteReportsMoreThanXDays(String sReportFolderPath, int iDays) {

		String sBeforeNDaysDate = DateUtils.getDateBeforeXDays("ddMMMyyy", iDays);

		File reportsFolder = new File(sReportFolderPath);

		List<File> reportsFoldersToDelete = new ArrayList<>();
		List<File> reportsFoldersNotToDelete = new ArrayList<>();

		for (File file : reportsFolder.listFiles()) {
			if (file.isDirectory()) {
				String sReportFolderDate = file.getName().split(" ")[0];
				if (DateUtils.isSecondDateBeforeFirstDate("ddMMMyyy", sBeforeNDaysDate, sReportFolderDate)) {
					reportsFoldersToDelete.add(file);

				} 
				else 
				{
					reportsFoldersNotToDelete.add(file);
				}
			}
		}


		for (int i = 0; i < reportsFoldersToDelete.size(); i++) {

			System.out.println(reportsFoldersToDelete.get(i).getName());

		}

		for (int i = 0; i < reportsFoldersNotToDelete.size(); i++) {
			System.out.println(reportsFoldersNotToDelete.get(i).getName());
		}

		for (int i = 0; i < reportsFoldersToDelete.size(); i++) {
			File file = reportsFoldersToDelete.get(i);
			System.out.println("Deleting \"" + file.getName() + "\" folder");
			try {
				FileDeleteStrategy.FORCE.delete(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
