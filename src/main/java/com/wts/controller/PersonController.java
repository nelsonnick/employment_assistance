package com.wts.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wts.entity.model.Operate;
import com.wts.entity.model.Person;
import com.wts.entity.model.User;
import com.wts.interceptor.Ajax;
import com.wts.interceptor.Login;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        Page<Person> recordPage= Person.dao.paginate(getParaToInt("pageCurrent"),getParaToInt("pageSize"),"SELECT person.id,person.person_name AS `name`,person.person_number AS `number`,type.describe AS type,category.describe AS category,location.`name` AS location",getSQL(getPara("queryName"),getPara("queryNumber"),getPara("queryCategory")));
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

    @Before({Tx.class, Ajax.class})
    public void save() {
        if (Person.dao.find("SELECT * FROM person WHERE person_number = ? AND state = 1", getPara("person[number]")).size() != 0) {
            renderText("该人员正在享受社保补贴");
        } else {
            Person person = new Person();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            person.set("person_name", getPara("person[name]"))
                    .set("person_number", getPara("person[number]"))
                    .set("person_phone", getPara("person[phone]"))
                    .set("person_address", getPara("person[address]"))
                    .set("person_company", getPara("person[company]"))
                    .set("person_timeA", sdf.format(Long.parseLong(getPara("person[timeA]"))))
                    .set("person_timeB", sdf.format(Long.parseLong(getPara("person[timeB]"))))
                    .set("person_job", getPara("person[job]"))
                    .set("person_state", getPara("person[state]"))
                    .set("person_type", getPara("person[type]"))
                    .set("person_category", getPara("person[category]"))
                    .set("person_security", getPara("person[security]"))
                    .set("person_disability", getPara("person[disability]"))
                    .set("person_childNum", getPara("person[childNum]"))
                    .set("person_remainMonth", getPara("person[remainMonth]"))
                    .set("person_remark", getPara("person[remark]"))
                    .set("spouse_name", getPara("spouse[name]"))
                    .set("spouse_number", getPara("spouse[number]"))
                    .set("spouse_company", getPara("spouse[company]"))
                    .set("spouse_time", sdf.format(Long.parseLong(getPara("spouse[time]"))))
                    .set("spouse_state", getPara("spouse[state]"))
                    .set("spouse_remark", getPara("spouse[remark]"))
                    .set("child1_name", getPara("child1[name]"))
                    .set("child1_number", getPara("child1[number]"))
                    .set("child1_company", getPara("child1[company]"))
                    .set("child1_time", sdf.format(Long.parseLong(getPara("child1[time]"))))
                    .set("child1_state", getPara("child1[state]"))
                    .set("child1_remark", getPara("child1[remark]"))
                    .set("child2_name", getPara("child2[name]"))
                    .set("child2_number", getPara("child2[number]"))
                    .set("child2_company", getPara("child2[company]"))
                    .set("child2_time", sdf.format(Long.parseLong(getPara("child2[time]"))))
                    .set("child2_state", getPara("child2[state]"))
                    .set("child2_remark", getPara("child2[remark]"))
                    .set("child3_name", getPara("child3[name]"))
                    .set("child3_number", getPara("child3[number]"))
                    .set("child3_company", getPara("child3[company]"))
                    .set("child3_time", sdf.format(Long.parseLong(getPara("child3[time]"))))
                    .set("child3_state", getPara("child3[state]"))
                    .set("child3_remark", getPara("child3[remark]"))
                    .set("child4_name", getPara("child4[name]"))
                    .set("child4_number", getPara("child4[number]"))
                    .set("child4_company", getPara("child4[company]"))
                    .set("child4_time", sdf.format(Long.parseLong(getPara("child4[time]"))))
                    .set("child4_state", getPara("child4[state]"))
                    .set("child4_remark", getPara("child4[remark]"))
                    .set("child5_name", getPara("child5[name]"))
                    .set("child5_number", getPara("child5[number]"))
                    .set("child5_company", getPara("child5[company]"))
                    .set("child5_time", sdf.format(Long.parseLong(getPara("child5[time]"))))
                    .set("child5_state", getPara("child5[state]"))
                    .set("child5_remark", getPara("child5[remark]"))
                    .set("location_id", ((User) getSessionAttr("user")).getLocationId())
                    .set("state",1)
                    .save();
            Operate operate = new Operate();
            operate.set("person_id", person.getId())
                    .set("type", "1")
                    .set("time", new Date())
                    .set("user_id", ((User) getSessionAttr("user")).getId())
                    .save();
            renderText("OK");
        }
    }
    @Before({Tx.class, Ajax.class})
    public void delete() {
        Person person = Person.dao.findById(getPara("id"));
        person.set("state",2).update();
        Operate operate = new Operate();
        operate.set("person_id", person.getId())
                .set("type", "1")
                .set("time", new Date())
                .set("user_id", ((User) getSessionAttr("user")).getId())
                .save();
        renderText("OK");
    }
    @Before({Tx.class, Ajax.class})
    public void edit() {
        Person person = Person.dao.findById(getPara("id"));
        person.set("person_name", getPara("person[name]"))
                .set("person_number", getPara("person[number]"))
                .set("person_phone", getPara("person[phone]"))
                .set("person_address", getPara("person[address]"))
                .set("person_company", getPara("person[company]"))
                .set("person_timeA", getPara("person[timeA]"))
                .set("person_timeB", getPara("person[timeB]"))
                .set("person_job", getPara("person[job]"))
                .set("person_state", getPara("person[state]"))
                .set("person_security", getPara("person[security]"))
                .set("person_disability", getPara("person[disability]"))
                .set("person_childNum", getPara("person[childNum]"))
                .set("person_remainMonth", getPara("person[remainMonth]"))
                .set("person_remark", getPara("person[remark]"))
                .set("spouse_name", getPara("spouse[name]"))
                .set("spouse_number", getPara("spouse[number]"))
                .set("spouse_company", getPara("spouse[company]"))
                .set("spouse_time", getPara("spouse[time]"))
                .set("spouse_state", getPara("spouse[state]"))
                .set("spouse_remark", getPara("spouse[remark]"))
                .set("child1_name", getPara("child1[name]"))
                .set("child1_number", getPara("child1[number]"))
                .set("child1_company", getPara("child1[company]"))
                .set("child1_time", getPara("child1[time]"))
                .set("child1_state", getPara("child1[state]"))
                .set("child1_remark", getPara("child1[remark]"))
                .set("child2_name", getPara("child2[name]"))
                .set("child2_number", getPara("child2[number]"))
                .set("child2_company", getPara("child2[company]"))
                .set("child2_time", getPara("child2[time]"))
                .set("child2_state", getPara("child2[state]"))
                .set("child2_remark", getPara("child2[remark]"))
                .set("child3_name", getPara("child3[name]"))
                .set("child3_number", getPara("child3[number]"))
                .set("child3_company", getPara("child3[company]"))
                .set("child3_time", getPara("child3[time]"))
                .set("child3_state", getPara("child3[state]"))
                .set("child3_remark", getPara("child3[remark]"))
                .set("child4_name", getPara("child4[name]"))
                .set("child4_number", getPara("child4[number]"))
                .set("child4_company", getPara("child4[company]"))
                .set("child4_time", getPara("child4[time]"))
                .set("child4_state", getPara("child4[state]"))
                .set("child4_remark", getPara("child4[remark]"))
                .set("child5_name", getPara("child5[name]"))
                .set("child5_number", getPara("child5[number]"))
                .set("child5_company", getPara("child5[company]"))
                .set("child5_time", getPara("child5[time]"))
                .set("child5_state", getPara("child5[state]"))
                .set("child5_remark", getPara("child5[remark]"))
                .set("location_id", ((User) getSessionAttr("user")).getLocationId())
                .update();
        Operate operate = new Operate();
        operate.set("person_id", person.getId())
                .set("type", "3")
                .set("time", new Date())
                .set("user_id", ((User) getSessionAttr("user")).getId())
                .save();
        renderText("OK");
    }
}
