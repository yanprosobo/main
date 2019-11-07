package eggventory.logic.commands;

import eggventory.ui.Ui;
import eggventory.model.items.StockType;
import eggventory.model.StockList;
import eggventory.storage.Storage;
import eggventory.commons.enums.CommandType;

/**
 * Command objects for searching for stocks by name.
 */
public class FindCommand extends Command {
    private String search;

    public FindCommand(CommandType type, String search) {
        super(type);
        this.search = search;
    }

    /**
     * Allows the user to search for stock descriptions that match a given string.
     * Prints the list of stocks that match. Alternatively prints a message if none are found.
     */
    @Override
    public String execute(StockList list, Ui ui, Storage storage) {
        String output;
        //int max = list.getQuantity(); Note by Rebs: changed variable name to be more specific.
        int stockTypeQuantity = list.getStockTypeQuantity();
        boolean found = false;

        String listString = "";
        for (int i = 0; i < stockTypeQuantity; i++) {
            int stockQuantity = list.get(i).getQuantity();
            for (int j = 0; j < stockQuantity; j++) {
                if (list.get(i).getStockWithIndex(j).getDescription().contains(search)) { //Only search the description.
                    // Adding task to print with associated index to final string
                    listString += (i + 1 + ". " + list.get(i).getStockWithIndex(j).toString() + "\n");
                    found = true;
                }
            }
        }

        if (!found) {
            output = "Sorry, I could not find any tasks containing the description \""
                    + search + "\".\nPlease try a different search string.";
            ui.print(output);
        } else {
            output = listString;
            ui.print(output);
        }
        return output;
    }
}

