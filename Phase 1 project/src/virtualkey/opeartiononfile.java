package virtualkey;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

public class opeartiononfile {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println( "Hello And Welcome to LockedMe.com" + "\n");
		System.out.println( "Developed By: Utkarsh Agarwal" + "\n");
		
		System.out.println("Please enter the path of the  your directory. ");
		String fileP = sc.nextLine();
		String your_filepath="";
		int r=0;
		
		if(fileP.contains("\\")) {
			your_filepath = fileP;
			r = displayOperations(sc);
			
		}
		else {
			System.out.println("Please enter a valid path and ReRun the Program..!!");
			System. exit(0);
		}
		
		

		while (true) {
			switch (r) {
			case 1: {
				System.out.println("Listing  files in the  your given directory...!!!");
				Set<String> l1 = displayFiles(your_filepath);
				Iterator itr = l1.iterator();
				while (itr.hasNext()) {
					System.out.println(itr.next());
				}
				break;

			}

			case 2: {

				System.out.println("Enter the file name that you want to add.");
				
				sc.nextLine();
				String fName = sc.nextLine();
				String escaped = your_filepath.replace("\\", "\\\\");
				addFile(escaped + "\\" + fName);
				System.out.println("Your file got successfully added.");
				break;
			}
			case 3: {
				System.out.println("Enter the fileName you want to search.");
				sc.nextLine();
				String fName = sc.nextLine();
				Set<String> l1 = displayFiles(your_filepath);

				checkFileAvailability(l1, fName);
				break;
			}
			case 4: {
				System.out.println("Enter the file name that you want to delete.");
				sc.nextLine();
				String escaped = your_filepath.replace("\\", "\\\\");
				String fName = sc.nextLine();
				deleteFiles(your_filepath + "\\" + fName);

				break;
			}
			case 5: {
				System.out.println("Thank You..!!");
				return;
			}

			default:
				break;
			}
			System.out.print("Do you want to perform more operations ?" + " Enter yes or no" + "\n");
			sc.nextLine();
			String s = sc.nextLine();
			if (s.equals("yes")) {
				r = displayOperations(sc);

			} else if (s.equals("no")) {
				break;
			} else {
				System.out.println("Not a valid answer.");
				break;
			}
		}
		System.out.println("Thank You !!");
		sc.close();

	}

	private static void addFile(String your_filepath) {
		try {

			FileOutputStream f = new FileOutputStream(your_filepath);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	private static int displayOperations(Scanner sc) {
		System.out.println("Please Enter The Number Of The Operation You Wish To Perform......." + "\n");
		System.out.println("Press 1.Retrieve all the files available in the particular directory." + "\n"
				+ "Press 2.If you want to add a file in the particular directory." + "\n"
				+ "Press 3.If you want to search a file in the particular directory." + "\n"
				+ "Press 4.If you want to delete the file in the particular directory." + "\n" + "Press 5.If you want to exit."
				+ "\n");
		int response = sc.nextInt();
		return response;
	}

	private static Set<String> displayFiles(String your_filepath) {
		File file = new File(your_filepath);
		String[] fileList = file.list();

		List<String> l = Arrays.asList(fileList);
		Collections.sort(l);

		Set<String> h = new TreeSet<String>();
		Iterator it = l.iterator();
		while (it.hasNext()) {
			h.add((String) it.next());
		}
		return h;

	}

	private static void checkFileAvailability(Set<String> hl, String fName) {

		if (hl.contains(fName)) {
			System.out.println("File Found");
		} else {
			System.out.println("Not Found");
		}

	}

	private static void deleteFiles(String your_filepath) {

		try {
			Files.deleteIfExists(Paths.get(your_filepath));
		} catch (NoSuchFileException e) {
			System.out.println("No such file/directory exists");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Directory is not empty.");
		} catch (IOException e) {

		}

		System.out.println("Deletion successful.");
	}

}

