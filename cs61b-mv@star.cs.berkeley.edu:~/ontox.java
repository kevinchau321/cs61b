public static void main(String[] arg) throws Exception {






class OneToX {
    public static void oneToX(int x) {
      if (x < 1) {
        return;
      }
      oneToX(x - 1);
      System.out.println(x);
    }
}
}
