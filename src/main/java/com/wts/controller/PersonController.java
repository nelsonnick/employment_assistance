package com.wts.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.wts.entity.model.Person;
import com.wts.entity.model.User;
import com.wts.interceptor.Ajax;
import com.wts.interceptor.Login;

@Before(Login.class)
public class PersonController extends Controller {
    private String getSQL(String queryName,String queryNumber,String queryCategory) {
        return "FROM (((person " +
                " LEFT JOIN location ON person.location_id = location.id)" +
                " LEFT JOIN type ON person.person_type = type.id)" +
                " LEFT JOIN category ON person.person_category = category.id)" +
                " WHERE (person.person_name LIKE '%" + queryName +
                "%' OR person.spouse_name LIKE '%" + queryName +
                "%' OR person.child1_name LIKE '%" + queryName +
                "%' OR person.child2_name LIKE '%" + queryName +
                "%' OR person.child3_name LIKE '%" + queryName +
                "%' OR person.child4_name LIKE '%" + queryName +
                "%' OR person.child5_name LIKE '%" + queryName +
                "%' OR person.person_number LIKE '%" + queryNumber +
                "%' OR person.spouse_number LIKE '%" + queryNumber +
                "%' OR person.child1_number LIKE '%" + queryNumber +
                "%' OR person.child2_number LIKE '%" + queryNumber +
                "%' OR person.child3_number LIKE '%" + queryNumber +
                "%' OR person.child4_number LIKE '%" + queryNumber +
                "%' OR person.child5_number LIKE '%" + queryNumber +
                "%' OR category.`describe` LIKE '%" + queryCategory +
                "%') AND person.state = 1 ORDER BY person.id DESC";
    }
    public void index() {
        render("/static/html/Business.html");
    }

    /**
     * 查询
     * queryName
     * queryNumber
     * queryCategory
     * pageCurrent
     * PageSize
     */
    @Before(Ajax.class)
    public void query() {
        Page<Person> recordPage= Person.dao.paginate(getParaToInt("pageCurrent"),getParaToInt("pageSize"),"SELECT person.id,person.person_name AS `name`,person.person_number AS `number`,person.person_type AS type,person.person_category AS category,location.`name` AS location",getSQL(getPara("queryName"),getPara("queryNumber"),getPara("queryCategory")));
        renderJson(recordPage.getList());
    }

    /**
     * 计数
     */
    @Before(Ajax.class)
    public void total() {
        String count = Db.queryLong("SELECT COUNT(*) " + getSQL(getPara("queryName"),getPara("queryNumber"),getPara("queryCategory"))).toString();
        renderText(count);
    }

    @Before(Ajax.class)
    public void save(){
        System.out.println(getPara("person[name]"));
    }
}
