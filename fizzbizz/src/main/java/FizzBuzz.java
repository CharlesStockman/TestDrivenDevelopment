public class FizzBuzz {

    public String convert(int number) {
        String result = "";
        if ( number == 3)
            result = "fizz";
        else
            result = String.valueOf(number);

        return result;
    }
}
