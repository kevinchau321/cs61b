  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    //isLeapYear tests
    System.out.println("\nisLeapYear(4):" + Date.isLeapYear(4));
    System.out.println("isLeapYear(3):" + Date.isLeapYear(3));
    System.out.println("isLeapYear(1800):" + Date.isLeapYear(1800));
    System.out.println("isLeapYear(1900):" + Date.isLeapYear(1900));
    System.out.println("isLeapYear(1600):" + Date.isLeapYear(1600));
    System.out.println("isLeapYear(2000):" + Date.isLeapYear(2000));
    System.out.println("Days in February, 1800:" + Date.daysInMonth(2, 1800));
    System.out.println("Days in February, 2012:" + Date.daysInMonth(2, 2012));
    System.out.println("1/1/0 is valid? " + Date.isValidDate(1,1,0));
    System.out.println("2/29/2013 is valid? " + Date.isValidDate(2,29,2013));
    System.out.println("11/32/2013 is valid? " + Date.isValidDate(11,32,2013));
    System.out.println("1/1/2014 is valid? " + Date.isValidDate(1,1,2014));
    System.out.println("4/31/2014 is valid? " + Date.isValidDate(4,31,2014));


    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));

    Date validDate1 = new Date("1/2/2015");
    Date validDate2 = new Date(3,5,2011);
    System.out.println("1/2/2015 - 3/5/2011 = "+validDate1.difference(validDate2));
    System.out.println("3/5/2011 - 1/2/2015 = "+validDate2.difference(validDate1));
    System.out.println("4/15/2000 - 1/2/2022 = "+ new Date(4,15,2000).difference(new Date(1,2,2022)));

    System.out.println("March 5th is day "+validDate2.dayInYear()+" in 2011");
    Date validDate3 = new Date(3,5,2012);
    System.out.println("March 5th is day "+validDate3.dayInYear()+" in 2012");
    //Date invalidDate2 = new Date("201/32/1093");
    //Date invalidDate3 = new Date("20/221/1093");
    //Date invalidDate4 = new Date("20/32/10933");
    //Date invalidDate1 = new Date(2,29,2013);
    //Date invalidDate5 = new Date("12/2/hello");
    //Date invalidDate6 = new Date("8/3/");
    //Date invalidDate6 = new Date("11/4/2013 AD");
    Date validDate7 = new Date("012/23/2014");  //leading zeros are allowed. 
    System.out.println(new Date(12,31,2011).dayInYear());
    System.out.println("Days in month 14: "+daysInMonth(14,1239));
    System.out.println("Days in month 0: " +daysInMonth(0,123));
  }