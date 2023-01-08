package com.project.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * FORM NHẬP CHUỖI (inputAndCheckString). FORM NHẬP SỐ DƯƠNG
 * (inputAndCheckPositiveNumber). FORM NHẬP SỐ ÂM (inputAndCheckNegativeNumber).
 * FORM NHẬP SỐ NẰM TRONG KHOẢNG MIN ĐẾN MAX (inputAndCheckMin_Max). FORM NHẬP
 * YES/NO (inputYesOrNo). FORM NHẬP SĐTVN (inputAndCheckPhoneVN). FORM NHẬP
 * EMAIL PHỔ BIẾN (inputAndCheckEmail). FORM NHẬP THEO PATERN CỦA BẠN
 * (inputAboutPatern)
 *
 * @author AnLee desu, Hajimemashite :).
 */
public class Input {

    //Chuỗi bắt đầu bằng chữ và không dư khoảng cách phía sau.
    public static final String PATERN_CHUOIBATDAUBANGCHU = "^[a-zA-Z]+(\\s\\w+)*$";
    //Số điện thoại Việt Nam.
    public static final String PATERN_DIENTHOAIVN = "^(0|\\\\+84)(\\\\s|\\\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\\\d)(\\\\s|\\\\.)?(\\\\d{3})(\\\\s|\\\\.)?(\\\\d{3})$";
    //Email bình thường nhất (Wxxx@Wxxx.Wxxx.Wxxx....).
    public static final String PATERN_EMAIL = "^[a-zA-Z]\\w*@[a-zA-Z]\\w*(\\.[a-zA-Z]\\w*)+$";
    //Tài khoản bắt đầu bằng chữ, viết liền không dấu từ 5 đến 9 ký tự.
    public static final String PATERN_TAIKHOAN = "^[a-zA-Z]\\w{5,9}$";
    //Mật khẩu bắt đầu bằng chữ viết liền không dấu từ 7 đến 11 kí tự.
    public static final String PATERN_MATKHAU = "^[a-zA-Z]\\w{7,11}$";

    /**
     * Xóa các khoảng trắng dư thừa trong chuỗi.
     *
     * @param input Chuỗi String.
     * @return Chuỗi sau khi sửa String.
     */
    public static String deleteRedundancySpace(String input) {
        return input.replaceAll("\\s\\s+", " ").trim();
    }

    /**
     * Cung cấp form nhập liệu (String) sau đó kiểm tra và chỉnh sửa dữ liệu đầu
     * vào kiểu String như sau: Bỏ trống hoặc chỉ chứa khoảng trắng (space,tab)
     * cho nhập lại. Chuỗi dư thừa các khoảng trắng sẽ chỉnh lại thằng chuỗi
     * đúng.
     *
     * @param titleInput Yêu cầu nhập (ex "Enter name:").
     * @param messageErr Thông báo lỗi hoặc hướng đẫn khi nhập sai (ex "Please
     * only enter number!").
     * @param scan Scanner
     * @return Chuỗi sau khi nhập đúng kiểu String (đã cắt bỏ space dư thừa).
     */
    public static String inputAndCheckString(String titleInput, String messageErr, Scanner scan) {

        System.out.print(titleInput);
        String input = scan.nextLine();
        input = deleteRedundancySpace(input);
        if (input.isEmpty() || !input.matches(PATERN_CHUOIBATDAUBANGCHU)) {
            System.out.println(messageErr);
            return inputAndCheckString(titleInput, messageErr, scan);
        } else {
            return input;
        }

    }

    /**
     * Cung cấp form nhập miễn không bỏ trống là được.
     * @param titleInput Yêu cầu nhập (ex "Enter name:").
     * @param messageErr Thông báo lỗi hoặc hướng đẫn khi nhập sai (ex "Please
     * only enter number!").
     * @param scan Scanner
     * @return Chuỗi sau khi nhập đúng kiểu String (đã cắt bỏ space dư thừa).
     */
    public static String inputAndCheckNotEmpty(String titleInput, String messageErr, Scanner scan) {

        System.out.print(titleInput);
        String input = scan.nextLine();
        input = deleteRedundancySpace(input);
        if (!input.matches("^\\S+(\\s*\\S*)*$")) {
            System.out.println(messageErr);
            return inputAndCheckNotEmpty(titleInput, messageErr, scan);
        } else {
            return input;
        }
    }

    /**
     * Cung cấp form nhập liệu (double) sau đó kiểm tra: Nếu đầu vào là chuỗi
     * hoặc số bé hơn (hoặc bằng) 0 thì cho nhập lại.
     *
     * @param titleInput Yêu cầu nhập (ex "Enter name:").
     * @param messageErr Thông báo lỗi hoặc hướng đẫn khi nhập sai (ex "Please
     * only enter number!").
     * @param scan Scanner.
     * @param equal true nếu lấy số 0, false nếu không lấy số 0.
     * @return Số đã nhập đúng(double).
     */
    public static double inputAndCheckPositiveNumber(String titleInput, String messageErr, Scanner scan, boolean equal) {
        System.out.print(titleInput);
        double input = 0;
        try {
            input = Double.parseDouble(scan.nextLine());
            if (equal) {
                while (input < 0) {
                    System.out.println(messageErr);
                    System.out.print(titleInput);
                    input = Double.parseDouble(scan.nextLine());
                }
                return input;
            } else {
                while (input <= 0) {
                    System.out.println(messageErr);
                    System.out.print(titleInput);
                    input = Double.parseDouble(scan.nextLine());
                }
                return input;
            }

        } catch (NumberFormatException e) {
            System.out.println(messageErr);
            return inputAndCheckPositiveNumber(titleInput, messageErr, scan, equal);
        }

    }

    /**
     * Cung cấp form nhập liệu (double) sau đó kiểm tra: Nếu đầu vào là chuỗi
     * hoặc số lớn hơn (hoặc bằng) 0 thì cho nhập lại.
     *
     * @param titleInput Yêu cầu nhập (ex "Enter name:").
     * @param messageErr Thông báo lỗi hoặc hướng đẫn khi nhập sai (ex "Please
     * only enter number!").
     * @param scan Scanner.
     * @param equal true nếu lấy số 0, false nếu không lấy số 0.
     * @return Số đã nhập đúng(double).
     */
    public static double inputAndCheckNegativeNumber(String titleInput, String messageErr, Scanner scan, boolean equal) {
        System.out.print(titleInput);
        double input = 0;
        try {
            input = Double.parseDouble(scan.nextLine());
            if (equal) {
                while (input > 0) {
                    System.out.println(messageErr);
                    System.out.print(titleInput);
                    input = Double.parseDouble(scan.nextLine());
                }
                return input;
            } else {
                while (input >= 0) {
                    System.out.println(messageErr);
                    System.out.print(titleInput);
                    input = Double.parseDouble(scan.nextLine());
                }
                return input;
            }

        } catch (NumberFormatException e) {
            System.out.println(messageErr);
            return inputAndCheckNegativeNumber(titleInput, messageErr, scan, equal);
        }
    }

    /**
     * Cung cấp form nhập liệu (double) từ khoảng min đến max sau đó kiểm tra:
     * Nếu đầu vào là chuỗi, số bé hơn min hoặc số lớn hơn max thì cho nhập lại.
     *
     * @param titleInput Yêu cầu nhập (ex "Enter name:").
     * @param messageErr Thông báo lỗi hoặc hướng đẫn khi nhập sai (ex "Please
     * only enter number!").
     * @param min Số bé.
     * @param max Số lớn.
     * @param scan Scanner.
     * @return Số đã nhập đúng (double).
     */
    public static double inputAndCheckMin_Max(String titleInput, String messageErr, double min, double max, Scanner scan) {
        System.out.print(titleInput);
        double input = 0;
        try {
            input = Double.parseDouble(scan.nextLine());
            if (input <= min || input >= max) {
                System.out.println(messageErr);
                return inputAndCheckMin_Max(titleInput, messageErr, min, max, scan);

            } else {
                return input;
            }
        } catch (NumberFormatException e) {
            System.out.println(messageErr);
            return inputAndCheckMin_Max(titleInput, messageErr, min, max, scan);
        }
    }

    /**
     * Cung cấp form nhập liệu (String) câu trả lời yes hoặc no. Nếu sai cho
     * nhập lại.
     *
     * @param titleInput Yêu cầu nhập (ex "Enter name:").
     * @param messageErr Thông báo lỗi hoặc hướng đẫn khi nhập sai (ex "Please
     * only enter number!").
     * @param scan Scanner.
     * @return Nhập đúng yes/y thì trả về true, nhập đúng no/n trả về false.
     */
    public static boolean inputYesOrNo(String titleInput, String messageErr, Scanner scan) {
        System.out.print(titleInput);
        String input = deleteRedundancySpace(scan.nextLine());
        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            return true;
        } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
            return false;
        } else {
            System.out.println(messageErr);
            return inputYesOrNo(titleInput, messageErr, scan);
        }

    }

    /**
     * Cung cấp form nhập liệu (String) số điện thoại ở VN. Nếu sai cho nhập
     * lại.
     * BETA Verion.
     *
     * @param titleInput Yêu cầu nhập (ex "Enter name:").
     * @param messageErr Thông báo lỗi hoặc hướng đẫn khi nhập sai (ex "Please
     * only enter number!").
     * @param scan Scanner.
     * @return Số điện thoại nếu nhập đúng (String).
     */
    public static String inputAndCheckPhoneVN(String titleInput, String messageErr, Scanner scan) {
        System.out.print(titleInput);
        String input = deleteRedundancySpace(scan.nextLine());
        if (input.matches(PATERN_DIENTHOAIVN)) {
            return input;
        } else {
            System.out.println(messageErr);
            return inputAndCheckPhoneVN(titleInput, messageErr, scan);
        }
    }

    /**
     * Cung cấp form nhập liệu (String) các loại email phổ biến. Nếu sai cno
     * nhập lại.
     *
     * @param titleInput Yêu cầu nhập (ex "Enter name:").
     * @param messageErr Thông báo lỗi hoặc hướng đẫn khi nhập sai (ex "Please
     * only enter number!").
     * @param scan Scanner.
     * @return Email nếu nhập đúng (String).
     */
    public static String inputAndCheckEmail(String titleInput, String messageErr, Scanner scan) {

        System.out.print(titleInput);
        String input = deleteRedundancySpace(scan.nextLine());
        if (input.matches(PATERN_EMAIL)) {
            return input;
        } else {
            System.out.println(messageErr);
            return inputAndCheckEmail(titleInput, messageErr, scan);
        }
    }

    /**
     * Cung cấp form nhập liệu (String) bất cứ chuỗi nào so khớp với patern bạn
     * truyền vào. Nếu sai cno nhập lại.
     *
     * @param titleInput Yêu cầu nhập (ex "Enter name:").
     * @param messageErr Thông báo lỗi hoặc hướng đẫn khi nhập sai (ex "Please
     * only enter number!").
     * @param patern Patern.
     * @param scan Scanner.
     * @return Chuỗi nếu nhập đúng (String).
     */
    public static String inputAboutPatern(String titleInput, String messageErr, String patern, Scanner scan) {
        System.out.print(titleInput);
        String input = deleteRedundancySpace(scan.nextLine());
        if (input.matches(patern)) {
            return input;
        } else {
            System.out.println(messageErr);
            return inputAboutPatern(titleInput, messageErr, patern, scan);
        }
    }

    /**
     * Cung cấp hàm cập nhật lại chữ viết hoa ở những chữ cái đầu trong tên.
     *
     * @param name Tên nhập vào (String).
     * @return Tên đã sửa (String).
     */
    public static String editName(String name) {
        name = deleteRedundancySpace(name).toLowerCase();
        String[] character = name.split("");

        character[0] = character[0].toUpperCase();
        String edited = character[0];
        for (int i = 1; i < character.length; i++) {
            if (character[i].equals(" ")) {
                character[i + 1] = character[i + 1].toUpperCase();
            }
            edited += character[i];
        }
        return edited;
    }

    /**
     * Cung cấp form nhập ngày tháng hợp lệ.
      * @param titleInput Yêu cầu nhập (ex "Enter name:").
     * @param messageErr Thông báo lỗi hoặc hướng đẫn khi nhập sai (ex "Please
     * only enter number!").
     * @param scan Scanner.
     * @param dayFormat Pattern ngày tháng(ex: dd/MM/yyyy).
     * @return ngày tháng (Date).
     */
    public static Date inputAndCheckDate(String titleInput, String messageErr, String dayFormat, Scanner scan) {
        System.out.print(titleInput);
        String input = deleteRedundancySpace(scan.nextLine());
        SimpleDateFormat pattern = new SimpleDateFormat(dayFormat);
        try {
            pattern.setLenient(false);
            return pattern.parse(input);
        } catch (ParseException e) {
            System.out.println(messageErr);
            return inputAndCheckDate(titleInput, messageErr, dayFormat, scan);
        }
    }


}
