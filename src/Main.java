import SerializeFileFactory.com.io.SerializeFileFactory;
import community.com.model.Contact;

import java.awt.*;
import java.sql.Connection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static HashMap<Integer, community.com.model.Contact> csdl = new HashMap<>();

    public static void menu() {
        System.out.println("1.Them");
        System.out.println("2.Xuat");
        System.out.println("3.Sua");
        System.out.println("4.Xoa");
        System.out.println("5.Tim");
       // System.out.println("6.Sap xep");
        System.out.println("7.Luu File");
        System.out.println("8.Doc file");
        System.out.println("9.Thoat");
        System.out.println("Chon:");
        int n = new Scanner(System.in).nextInt();
        switch (n) {
            case 1 -> them();
            case 2 -> xuat();
            case 3 -> sua();
            case 4 -> xoa();
            case 5 -> tim();
            case 7 -> luuFile();
            case 8 -> docFile();
            case 9 -> {
                System.err.println("Xin cam on");
                System.exit(0);
            }
        }
    }

    private static void docFile() {
        System.out.println("ban chac chan muon doc file khong y/n");
        String choose=new Scanner(System.in).nextLine();
        switch (choose){
            case "y"->{
                csdl = SerializeFileFactory.readFile("D://Contact.dat");
                System.out.println("doc du lieu thanh cong");
            }
            default -> {
                System.out.println("ban chua doc file, ban se quay ve menu");
            }
        }



    }

    private static void luuFile() {

        System.out.println("ban chac chan muon save file khong y/n");
        String choose=new Scanner(System.in).nextLine();
        switch (choose){
            case "y"->{
                SerializeFileFactory.saveFile(csdl, "D://Contact.dat");
                System.out.println("luu du lieu thanh cong");
            }
            default -> {
                System.out.println("ban chua doc file, ban se quay ve menu");
            }
        }

    }

    private static void tim() {
        System.out.println("ban muon tim kiem theo:");
        System.out.println("1.tim theo ten");
        System.out.println("2.tim theo so dien thoai");
        int choose=new Scanner(System.in).nextInt();
        switch (choose){
            case 1->{
                System.out.println("Nhap phone:");
                String phone = new Scanner(System.in).nextLine();
                for (Map.Entry<Integer, Contact> item : csdl.entrySet()) {
                    if (item.getValue().getPhone().startsWith(phone)) {
                        System.out.println(item.getValue());
                    }
                }
            }
            case 2->{
                System.out.println("Nhap ten:");
                String name = new Scanner(System.in).nextLine();
                for (Map.Entry<Integer, Contact> item : csdl.entrySet()) {
                    if (item.getValue().getName().startsWith(name)) {
                        System.out.println(item.getValue());
                    }
                }
            }


        }




    }

    private static void xoa() {
        boolean checkExist = false;
        Integer wantDeleteKey = null;
        System.out.println("Nhap so dien thoai muon xoa");
        String deleteNumber = new Scanner(System.in).nextLine();
        Set<Map.Entry<Integer, Contact>> entries = csdl.entrySet();
        for (Map.Entry<Integer, Contact> entry : entries
        ) {
            Contact contact = entry.getValue();
            String phone = contact.getPhone();
            Integer key=entry.getKey();
            if (deleteNumber.equals(phone)) {
                checkExist = true;
                wantDeleteKey=key;
            }
        }
        if (checkExist==true){
            System.out.println("ban chac chan muon xoa khong y/n");
            String choose=new Scanner(System.in).nextLine();
            switch (choose){
                case "y"->{
                 csdl.remove(wantDeleteKey);
                }
                default->{
                }


            }

        }
        else {
            System.out.println("khong tim duoc danh ba voi sdt tren");
            xoa();
        }



    }

    private static void sua() {
        boolean checkExist = false;
        Contact fixContact = new Contact();
        System.out.println("nhap so dien thoai nguoi can sua");
        String wantCheckPhoneNumber = new Scanner(System.in).nextLine();
        Set<Map.Entry<Integer, Contact>> entries = csdl.entrySet();
        for (Map.Entry<Integer, Contact> entry : entries
        ) {
            fixContact = entry.getValue();
            String phoneNumber = fixContact.getPhone();
            if (wantCheckPhoneNumber.equals(phoneNumber)) {
                checkExist = true;
                break;
            }
        }
        if (checkExist == true) {
            System.out.println("moi ban sua ten");
            String name = new Scanner(System.in).nextLine();
            fixContact.setName(name);

            System.out.println("moi ban sua gioi tinh");
            String sex = new Scanner(System.in).nextLine();
            fixContact.setSex(sex);

            System.out.println("moi ban sua dia chi");
            String address = new Scanner(System.in).nextLine();
            fixContact.setAddress(address);

            System.out.println("moi ban sua ngay sinh");
            String dateOfBirth = new Scanner(System.in).nextLine();
            fixContact.setDateOfBirth(dateOfBirth);

            System.out.println("moi ban sua Email");
            String email = new Scanner(System.in).nextLine();
            fixContact.setEmail(email);

        } else {
            System.out.println("khong tim duoc so dien thoai, vui long nhap lai");
            sua();
        }


    }

    private static void xuat() {
        System.out.println("danh sach danh ba:");
        for (Map.Entry<Integer, community.com.model.Contact> item : csdl.entrySet()) {
            System.out.println(item.getValue());
        }
    }

    private static void them() {
        System.out.println("Nhap ma:");
        int ma = new Scanner(System.in).nextInt();
        System.out.println("Nhap Ten:");
        String ten = new Scanner(System.in).nextLine();
        System.out.println("Nhap Phone:");
        String phone = checkPhone();
        System.out.println("Nhap ngay sinh");
        String dateOfBirth = new Scanner(System.in).nextLine();
        System.out.println("nhap email");
        String email = checkEmail();
        System.out.println("nhap gioi tinh");
        String sex = new Scanner(System.in).nextLine();
        System.out.println("nhap dia chi");
        String address = new Scanner(System.in).nextLine();
        community.com.model.Contact contact = new community.com.model.Contact(ma, ten, phone, dateOfBirth, email, sex, address);
        if (!csdl.containsKey(contact.getId())) {
            csdl.put(contact.getId(), contact);
        }
    }

    private static String checkPhone() {
        String phone=new Scanner(System.in).nextLine();
        String PHONEPATTERN =
                "[0-9]";
        Pattern pattern = Pattern.compile(PHONEPATTERN);
        Matcher matcher=pattern.matcher(phone);
        if(matcher.find()==false){
            System.out.println("nhap sai dinh dang, vui long nhap lai");
            checkPhone();
        }
        else return phone;


        return null;
    }

    private static String checkEmail() {
        String email=new Scanner(System.in).nextLine();
        String EMAIL_PATTERN =
                "@";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher=pattern.matcher(email);
        if(matcher.find()==false){
            System.out.println("nhap sai dinh dang, vui long nhap lai");
            checkEmail();
        }
        else return email;


        return null;
    }


    public static void main(String[] args) {
        while (true) {
            menu();
        }
    }
}
