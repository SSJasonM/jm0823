package com.demo;

import com.demo.datatypes.Tool;
import com.demo.datatypes.ToolType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Class that sets up the hard-coded data for this demo app.
 * In a more robust app with database integration, this would probably be replaced
 * with services to query the database for data instead.
 */
public class ToolData {
    public static final String BRAND_STIHL = "Stihl";
    public static final String BRAND_WERNER = "Werner";
    public static final String BRAND_DEWALT = "DeWalt";
    public static final String BRAND_RIDGID = "Ridgid";
    public static final String TYPE_CHAINSAW = "Chainsaw";
    public static final String TYPE_LADDER = "Ladder";
    public static final String TYPE_JACKHAMMER = "Jackhammer";
    private final Collection<Tool> tools = new ArrayList<>();

    /**
     * Constructor initializes the test data into a collection of Tools when class is instantiated.
     * Requested tool is selected from the collection using the getTool() method by providing a specific tool code.
     */
    public ToolData() {
        var chainSaw = new ToolType(TYPE_CHAINSAW, new BigDecimal("1.49"), true, false, true);
        var ladder = new ToolType(TYPE_LADDER, new BigDecimal("1.99"), true, true, false);
        var jackhammer = new ToolType(TYPE_JACKHAMMER, new BigDecimal("2.99"), true, false, false);

        this.tools.add(new Tool("CHNS", BRAND_STIHL, chainSaw));
        this.tools.add(new Tool("LADW", BRAND_WERNER, ladder));
        this.tools.add(new Tool("JAKD", BRAND_DEWALT, jackhammer));
        this.tools.add(new Tool("JAKR", BRAND_RIDGID, jackhammer));
    }

    /**
     * Returns a tool object constructed with data based on the provided tool code
     * @param code Tool code to lookup
     * @return Tool object to return.  Returns NULL if given code is not found.
     */
    public Tool getTool(String code) {
        return this.tools.stream().filter(x -> Objects.equals(x.code(), code)).findFirst().orElse(null);
    }
}
