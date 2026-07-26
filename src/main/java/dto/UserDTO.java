package dto;

/**
 * بتمثل صف في جدول users.
 * الجدول ده مخصص للمديرين بتوع الـ HR بس، وهو Read-Only بالنسبة للموقع ده
 * (بيتقرا منه بس وقت تسجيل الدخول، ومفيش أي إضافة أو تعديل أو حذف عليه من هنا).
 */
public class UserDTO {

    private int id;
    private String name;
    private String email;

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
