package com.java.items.demo.mybats_demo;

/**
 * @Auther: cdc
 * @Date: 2020/4/13 10:44
 * @Description: mybatis 的foreach优化
 */
public class foreachPerformanceDemo {

    public static void main(String[] args) {
        // 拼接 SQL
        StringBuffer sb = new StringBuffer();
        sb.append("(");
        for (int i = 1; i < 10; i++) {
            sb.append(i).append(",");
        }
        System.out.println(sb.toString());
        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append(")");
        System.out.println(sb.toString());
        // 最终的 SQL 为 (1,2,3,4,5...)
        long start2 = System.currentTimeMillis();


    }

    // 1.dao添加方法：List<Person> queryPersonByIds2(@Param("ids") String ids);
    // 2. 配置文件sql: <select id="queryPersonByIds2" parameterType="String" resultMap="queryPersonMap">
    //	select * from person where 1=1
    //	<if test="ids != null and ids != ''">
    //	 and id in ${ids}
    //	</if>
    //</select>
}
