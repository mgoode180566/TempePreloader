/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sml.tempepreloader;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.log4j.Logger;

/**
 *
 * @author michaelgoode
 */
public class GroupList extends ArrayList {
    
    private static final Logger log = Logger.getLogger( GroupList.class.getName() );

    public GroupList() {
        super();
    }

    public void addGroup(Group group, String line, String contract, long qty, SkuValues values, String family ) {

        // check that a group already exists. if so add the skuline to the group
        // if a new group then just add it with the skuline

        Iterator iter = this.iterator();

        boolean matchFound = false;

        while (iter.hasNext()) {

            Group aGroup = (Group) iter.next();

            if (aGroup.equals(group)) { // found a matching group

                if (!aGroup.contracts.contains(contract)) {
                    aGroup.contracts.add(contract);
                } 
                
                aGroup.addSKU(new SKULine( line, group.getSize(), qty, values,  family));
                
                matchFound = true;

            }

        }

        if (!matchFound) { // a new group is found
            log.debug(contract);
            group.contracts.add(contract);
            group.addSKU(new SKULine( line, group.getSize(), qty, values, family ));
            this.add(group);
        }

    }

    public void saveGroups(String fileName,  IgnoreCalloutList ignoreList, HashMap<String,Callout> calloutMap, ArrayList<String> doubles) {

        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));

            Iterator iter = this.iterator();
            while (iter.hasNext()) {

                Group aGroup = (Group) iter.next();

                aGroup.saveGroup(out,ignoreList,calloutMap,doubles);

            }

        } catch (IOException ex) {

            log.debug("Error saving group " + ex.getMessage());

        } finally {

            try {
                out.close();
            } catch (IOException ex) {
                log.debug("Error closing file " + ex.getMessage());
            }
        }

    }
    
    public void expandGroups() {
        
        Iterator iter = this.iterator();
        
        while (iter.hasNext()) {
            
            Group g = (Group) iter.next();
            
            g.expandGroup();
            
        }
        
    }
    
}