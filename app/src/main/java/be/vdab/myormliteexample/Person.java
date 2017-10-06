package be.vdab.myormliteexample;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by vdabcursist on 06/10/2017.
 */

public class Person {

    @DatabaseField(generatedId = true)
    private int acountId;

    @DatabaseField
    private String name;

    public int getAcountId() {
        return acountId;
    }

    public void setAcountId(int acountId) {
        this.acountId = acountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
