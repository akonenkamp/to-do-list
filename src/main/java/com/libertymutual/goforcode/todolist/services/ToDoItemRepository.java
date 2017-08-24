// ToDoItemRepository.java
package com.libertymutual.goforcode.todolist.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.libertymutual.goforcode.todolist.models.ToDoItem;

@Service
public class ToDoItemRepository {

    private int nextId = 1;

    /**
     * Get all the items from the file. 
     * @return A list of the items. If no items exist, returns an empty list.
     */
    public List<ToDoItem> getAll() {
    	List<ToDoItem> listOfItems = new ArrayList<ToDoItem>();
        // Replace this with something meaningful
//    	FileReader inThing = null;
		try (Reader inThing = new FileReader("to-do-list.csv")) {
//			inThing = new FileReader("path/to/file.csv");
	    	Iterable<CSVRecord> records = null;
			records = CSVFormat.DEFAULT.parse(inThing);
			
			for (CSVRecord record : records) {
				ToDoItem item = new ToDoItem();
				item.setId(Integer.parseInt(record.get(0)));
				item.setText(record.get(1));
				item.setComplete(Boolean.parseBoolean(record.get(2)));
				listOfItems.add(item);
			}
	    	}  catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("hit file not found exception");
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("file not found");
		}
		

        return listOfItems;
    }

    /**
     * Assigns a new id to the ToDoItem and saves it to the file.
     * @param item The to-do item to save to the file.
     */
    public void create(ToDoItem item) {
        String[] stringArray = new String [3];
    	item.setId(nextId);
    	item.setComplete(false);
    	nextId += 1; 
//       Fill this in with something meaningful
    	
    	Writer out = null;
    	try {
		  out = new FileWriter ("to-do-list.csv", true);
		  
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("hit exception");
		}
    	try {
    	  stringArray[0] = (Integer.toString(item.getId()));
    	  stringArray[1] = (item.getText());
    	  stringArray[2] = (Boolean.toString(item.isComplete()));
    	  CSVPrinter printer = CSVFormat.DEFAULT.print(out);
    	  printer.printRecord(stringArray);
    	  out.close();
    	} catch (IOException e) {
    		System.out.println("hit excetion");
    	}
    	}

    /*
     * Gets a specific ToDoItem by its id.
     * @param id The id of the ToDoItem.
     * @return The ToDoItem with the specified id or null if none is found.
     */
    public ToDoItem getById(int id) {
        // Replace this with something meaningful
        List<ToDoItem> currentListOfItems = getAll();
		
    for (ToDoItem tempItem : currentListOfItems) {
    	if (tempItem.getId() == id) {
    		return tempItem;
    	}
    }
    
    return null;
    }
    
    
    /**
     * Updates the given to-do item in the file.
     * @param item The item to update.
     */
    public void update(ToDoItem item) {
        // Fill this in with something meaningful
    	List<ToDoItem> currentListOfItems = getAll();
    	List<ToDoItem> updatedListOfItems = new ArrayList<ToDoItem>();
    	int i = 0;
    	for (ToDoItem thisThing : currentListOfItems) {
    		if ( thisThing.getId() == item.getId()) {
    			updatedListOfItems.add(item);
    		} else {
    			updatedListOfItems.add(thisThing);
    		}
    	}
    	
    	for (ToDoItem thisThing : updatedListOfItems) {
    		if (i == 0) {
    			i += 1;
    			Writer out = null;
                String[] stringArray = new String [3];

            	try {
        		  out = new FileWriter ("to-do-list.csv", false);
        		  
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
//        			e.printStackTrace();
        			System.out.println("hit exception");
        		}
            	try {
            	  stringArray[0] = (Integer.toString(thisThing.getId()));
            	  stringArray[1] = (thisThing.getText());
            	  stringArray[2] = (Boolean.toString(thisThing.isComplete()));
            	  CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            	  printer.printRecord(stringArray);
            	  out.close();
            	} catch (IOException e) {
            		System.out.println("hit exception");
            	}
    		} else {
        	Writer out = null;
            String[] stringArray = new String [3];

        	try {
    		  out = new FileWriter ("to-do-list.csv", true);
    		  
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
//    			e.printStackTrace();
    			System.out.println("hit exception");
    		}
        	try {
        	  stringArray[0] = (Integer.toString(thisThing.getId()));
        	  stringArray[1] = (thisThing.getText());
        	  stringArray[2] = (Boolean.toString(thisThing.isComplete()));
        	  CSVPrinter printer = CSVFormat.DEFAULT.print(out);
        	  printer.printRecord(stringArray);
        	  out.close();
        	} catch (IOException e) {
        		System.out.println("hit exception");
        	}
        	}
    	}
    	}
    }
