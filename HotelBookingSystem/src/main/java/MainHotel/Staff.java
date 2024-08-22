/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author alex
 */
public class Staff extends User implements ID{
    
    private final String staffID;
   
    
    public Staff(String userName, String password, String name, String phone, String email) {
        super(userName, password, name, phone, email);
        super.setType(UserType.STAFF);
        this.staffID = idGenerator();
    }
 
   
    @Override
    public final String idGenerator() {
        String output = "STF-";
        int id = (int) (Math.random() * 1001);
        return output + id;
    }

    /**
     * @return the staffID
     */
    public String getStaffID() {
        return staffID;
    }
}
