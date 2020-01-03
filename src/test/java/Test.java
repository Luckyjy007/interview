import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2020-01-03 18:30
 **/
public class Test {


    //insert into table_load_info (Source_DB_ID,Source_DB,Source_Table,Target_DB,Target_Table,Init_Flag,Join_Column,Del_Flag,Proc_Flag)values (100,'asset_core_0000','asset_service_fee_divide_paid_up_0','zcfw_sa','sa03_account_customer_branch_sub_log_add',3,'id',0,1);

    @org.junit.Test
    public  void test1() throws IOException {

        List<String> strings = FileUtils.readLines(new File("/Users/apple/project/interview/src/main/resources/table"), "utf-8");

        ArrayList<String> list = new ArrayList<>();

        for (String line:strings){



            String[] split = line.split("\\.");

            String sdb = split[0];
            String stb = split[1];
            int id = Integer.parseInt(sdb.substring(sdb.length() - 1, sdb.length()));

            String sql ="insert into table_load_info (Source_DB_ID,Source_DB,Source_Table,Target_DB,Target_Table,Init_Flag,Join_Column,Del_Flag,Proc_Flag)values ("+id+",'"+sdb+"','"+stb+"','zcfw_sa','sa03_account_customer_branch_sub_log_add',3,'id',0,1);";

            System.out.println(sql);

            FileUtils.write(new File("/Users/apple/project/interview/src/main/resources/table2"),sql+"\n","utf-8",true);


        }
    }
}