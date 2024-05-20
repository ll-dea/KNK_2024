//package Model.Filter;
//
//public class UserFilter {
//    private String lenda;
//    private String profi;
//    private String from;
//
//    public UserFilter (String lenda, String profi, String from){
//        this.lenda = lenda;
//        this.profi = profi;
//        this.from = from;
//    }
//
//    public String getBuildQuery(){
//        /*
//        Select * FROM USER
//        WHERE 1 == 1
//        AND EMAIL LIKE FIL%;
//         */
//        String  query = "";
//        if (lenda.isEmpty()){
//            query += "AND email like' " + profi
//        }
//
//        return query;
//    }
//
//}

//to get better points add a abstract class named filter, every other class extends from filter. Its better to have abstract classes