
package com.ground0.generictechfest.network.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Workshops {

    @SerializedName("workshop_list")
    @Expose
    private List<WorkshopList> workshopList = new ArrayList<WorkshopList>();

    /**
     * 
     * @return
     *     The workshopList
     */
    public List<WorkshopList> getWorkshopList() {
        return workshopList;
    }

    /**
     * 
     * @param workshopList
     *     The workshop_list
     */
    public void setWorkshopList(List<WorkshopList> workshopList) {
        this.workshopList = workshopList;
    }

}
