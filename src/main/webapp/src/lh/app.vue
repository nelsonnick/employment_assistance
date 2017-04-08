<template>
  <div>
    <Row type="flex" justify="center" align="middle">
      <Col span="4">
      <Button type="text" disabled>申请类别</Button>
      </Col>
      <Col span="20">
      <Radio-group v-model="category" type="button">
        <Radio label="1">灵活就业人员</Radio>
        <Radio label="2">公益性岗位</Radio>
        <Radio label="3">企业吸纳</Radio>
      </Radio-group>
      </Col>
    </Row>
    <br>
    <Row type="flex" justify="center" align="middle">
      <Col span="4">
      <Button type="text" disabled>申请条件</Button>
      </Col>
      <Col span="20">
      <Radio-group v-model="type" type="button">
        <Radio label="1">城镇零就业家庭的4050失业人员</Radio>
        <Radio label="2">{{category === '3' ? '抚养未成年子女单亲家庭成员的失业人员' : '抚养未成年子女单亲家庭成员的4050失业人员'}}</Radio>
        <Radio label="3">{{category === '3' ? '享受城市居民最低生活保障的失业人员' : '享受城市居民最低生活保障的4050失业人员'}}</Radio>
        <Radio label="4">{{category === '3' ? '持有《中华人民共和国残疾人证》的失业人员' : '持有《中华人民共和国残疾人证》的4050失业人员'}}</Radio>
        <Radio label="5">特困家庭未就业的高校毕业生</Radio>
        <Radio label="6">城镇登记失业的成年后孤儿</Radio>
        <Radio label="7" v-if="category === '3'">4050失业人员且连续失业半年以上</Radio>
      </Radio-group>
      </Col>
    </Row>
    <br>
    <Row type="flex" justify="center" align="middle">
      <Col span="4">
      <Button type="text" disabled>家庭成员</Button>
      </Col>
      <Col span="20">
      <Button shape="circle" type="info"  @click="person_modal = true">{{person_name === '' ? '申请人' : person_name}}</Button>
      <Button shape="circle" type="success" v-if="type === '1' && person_marriage === '2'" @click="spouse_modal = true">{{spouse_name === '' ? '配偶' : spouse_name}}</Button>
      <Button shape="circle" type="warning" v-if="(type === '1' || type === '2') && (childNum == 1 || childNum == 2 || childNum == 3 || childNum == 4 || childNum == 5)" @click="child1_modal = true">{{child1_name === '' ? '子女1' : child1_name}}</Button>
      <Button shape="circle" type="warning" v-if="(type === '1' || type === '2') && (childNum == 2 || childNum == 3 || childNum == 4 || childNum == 5)" @click="child2_modal = true">{{child2_name === '' ? '子女2' : child2_name}}</Button>
      <Button shape="circle" type="warning" v-if="(type === '1' || type === '2') && (childNum == 3 || childNum == 4 || childNum == 5)" @click="child3_modal = true">{{child3_name === '' ? '子女3' : child3_name}}</Button>
      <Button shape="circle" type="warning" v-if="(type === '1' || type === '2') && (childNum == 4 || childNum == 5)" @click="child1_moda4 = true">{{child4_name === '' ? '子女4' : child4_name}}</Button>
      <Button shape="circle" type="warning" v-if="(type === '1' || type === '2') && childNum == 5" @click="child5_modal = true">{{child5_name === '' ? '子女5' : child5_name}}</Button>
      </Col>
    </Row>
    <br>
    <Row type="flex" justify="center" align="middle">
      <Col span="4">
      <Button type="text" disabled>留存材料</Button>
      </Col>
      <Col span="20">
      <Tag color="blue">{{person_name === '' ? '本人' : person_name}}-身份证复印件一份</Tag>
      <Tag color="blue">{{person_name === '' ? '本人' : person_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="blue">{{person_name === '' ? '本人' : person_name}}-城镇登记失业证明一份</Tag>
      <Tag color="blue" v-if="type === '3'">{{person_name === '' ? '本人' : person_name}}-《城市居民最低生活保障证》复印件一份</Tag>
      <Tag color="blue" v-if="type === '3'">{{person_name === '' ? '本人' : person_name}}-《城市居民最低生活保障证》登记中的家庭成员户口簿复印件一份</Tag>
      <Tag color="blue" v-if="type === '4'">{{person_name === '' ? '本人' : person_name}}-《中华人民共和国残疾人证》复印件一份</Tag>
      <Tag color="blue" v-if="type === '5'">{{person_name === '' ? '本人' : person_name}}-《城市居民最低生活保障证》复印件一份</Tag>
      <Tag color="blue" v-if="type === '5'">{{person_name === '' ? '本人' : person_name}}-《济南市特困职工优待证》复印件一份</Tag>
      <Tag color="blue" v-if="type === '5'">{{person_name === '' ? '本人' : person_name}}-《毕业证》复印件一份</Tag>
      <Tag color="blue" v-if="type === '5'">{{person_name === '' ? '本人' : person_name}}-《报到证》复印件一份</Tag>
      <Tag color="blue" v-if="type === '6'">{{person_name === '' ? '本人' : person_name}}-县级以上民政部门认定的孤儿证明复印件一份</Tag>
      <Tag color="blue" v-if="person_marriage === '1'">{{person_name === '' ? '本人' : person_name}}-单身证明</Tag>
      <Tag color="green" v-if="type === '1' && person_marriage === '2'">{{spouse_name === '' ? '配偶' : spouse_name}}-身份证复印件一份</Tag>
      <Tag color="green" v-if="type === '1' && person_marriage === '2'">{{spouse_name === '' ? '配偶' : spouse_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="green" v-if="type === '1' && person_marriage === '2' && spouse_retire === '1'">{{spouse_name === '' ? '配偶' : spouse_name}}-城镇登记失业证明一份</Tag>
      <Tag color="green" v-if="type === '1' && person_marriage === '2' && spouse_retire === '2'">{{spouse_name === '' ? '配偶' : spouse_name}}-退休证复印件一份</Tag>
      <Tag color="green" v-if="(type === '1' || type === '2') && person_marriage === '3'">{{person_name === '' ? '本人' : person_name}}-离婚证复印件一份或离婚协议书复印件一份</Tag>
      <Tag color="green" v-if="(type === '1' || type === '2') && person_marriage === '4'">{{spouse_name === '' ? '配偶' : spouse_name}}-死亡证明一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && person_marriage === '1' && (childNum == 1 || childNum == 2 || childNum == 3 || childNum == 4 || childNum == 5)">{{child1_name === '' ? '子女一' : child1_name}}-抚养证明</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '1'">{{child1_name === '' ? '子女一' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '1'">{{child1_name === '' ? '子女一' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '1'">{{child1_name === '' ? '子女一' : child1_name}}-在校证明</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '2'">{{child1_name === '' ? '子女一' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '2'">{{child1_name === '' ? '子女一' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '2'">{{child1_name === '' ? '子女一' : child1_name}}-结婚证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '3'">{{child1_name === '' ? '子女一' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '3'">{{child1_name === '' ? '子女一' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '3'">{{child1_name === '' ? '子女一' : child1_name}}-城镇登记失业证明一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '4'">{{child1_name === '' ? '子女一' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '4'">{{child1_name === '' ? '子女一' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '4'">{{child1_name === '' ? '子女一' : child1_name}}-离婚证复印件一份或离婚协议书复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '5'">{{child1_name === '' ? '子女一' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '5'">{{child1_name === '' ? '子女一' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child1_state === '5'">{{child1_name === '' ? '子女一' : child1_name}}-死亡证明一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && person_marriage === '1' && (childNum == 2 || childNum == 3 || childNum == 4 || childNum == 5)">{{child2_name === '' ? '子女二' : child2_name}}-抚养证明</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '1'">{{child2_name === '' ? '子女二' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '1'">{{child2_name === '' ? '子女二' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '1'">{{child2_name === '' ? '子女二' : child1_name}}-在校证明</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '2'">{{child2_name === '' ? '子女二' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '2'">{{child2_name === '' ? '子女二' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '2'">{{child2_name === '' ? '子女二' : child1_name}}-结婚证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '3'">{{child2_name === '' ? '子女二' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '3'">{{child2_name === '' ? '子女二' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '3'">{{child2_name === '' ? '子女二' : child1_name}}-城镇登记失业证明一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '4'">{{child2_name === '' ? '子女二' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '4'">{{child2_name === '' ? '子女二' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '4'">{{child2_name === '' ? '子女二' : child1_name}}-离婚证复印件一份或离婚协议书复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '5'">{{child2_name === '' ? '子女二' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '5'">{{child2_name === '' ? '子女二' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child2_state === '5'">{{child2_name === '' ? '子女二' : child1_name}}-死亡证明一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && person_marriage === '1' && (childNum == 3 || childNum == 4 || childNum == 5)">{{child3_name === '' ? '子女三' : child3_name}}-抚养证明</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '1'">{{child3_name === '' ? '子女三' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '1'">{{child3_name === '' ? '子女三' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '1'">{{child3_name === '' ? '子女三' : child1_name}}-在校证明</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '2'">{{child3_name === '' ? '子女三' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '2'">{{child3_name === '' ? '子女三' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '2'">{{child3_name === '' ? '子女三' : child1_name}}-结婚证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '3'">{{child3_name === '' ? '子女三' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '3'">{{child3_name === '' ? '子女三' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '3'">{{child3_name === '' ? '子女三' : child1_name}}-城镇登记失业证明一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '4'">{{child3_name === '' ? '子女三' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '4'">{{child3_name === '' ? '子女三' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '4'">{{child3_name === '' ? '子女三' : child1_name}}-离婚证复印件一份或离婚协议书复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '5'">{{child3_name === '' ? '子女三' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '5'">{{child3_name === '' ? '子女三' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child3_state === '5'">{{child3_name === '' ? '子女三' : child1_name}}-死亡证明一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && person_marriage === '1' && (childNum == 4 || childNum == 5)">{{child4_name === '' ? '子女四' : child4_name}}-抚养证明</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '1'">{{child4_name === '' ? '子女四' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '1'">{{child4_name === '' ? '子女四' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '1'">{{child4_name === '' ? '子女四' : child1_name}}-在校证明</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '2'">{{child4_name === '' ? '子女四' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '2'">{{child4_name === '' ? '子女四' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '2'">{{child4_name === '' ? '子女四' : child1_name}}-结婚证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '3'">{{child4_name === '' ? '子女四' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '3'">{{child4_name === '' ? '子女四' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '3'">{{child4_name === '' ? '子女四' : child1_name}}-城镇登记失业证明一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '4'">{{child4_name === '' ? '子女四' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '4'">{{child4_name === '' ? '子女四' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '4'">{{child4_name === '' ? '子女四' : child1_name}}-离婚证复印件一份或离婚协议书复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '5'">{{child4_name === '' ? '子女四' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '5'">{{child4_name === '' ? '子女四' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child4_state === '5'">{{child4_name === '' ? '子女四' : child1_name}}-死亡证明一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && person_marriage === '1' && childNum == 5">{{child5_name === '' ? '子女五' : child5_name}}-抚养证明</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '1'">{{child5_name === '' ? '子女五' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '1'">{{child5_name === '' ? '子女五' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '1'">{{child5_name === '' ? '子女五' : child1_name}}-在校证明</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '2'">{{child5_name === '' ? '子女五' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '2'">{{child5_name === '' ? '子女五' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '2'">{{child5_name === '' ? '子女五' : child1_name}}-结婚证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '3'">{{child5_name === '' ? '子女五' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '3'">{{child5_name === '' ? '子女五' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '3'">{{child5_name === '' ? '子女五' : child1_name}}-城镇登记失业证明一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '4'">{{child5_name === '' ? '子女五' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '4'">{{child5_name === '' ? '子女五' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '4'">{{child5_name === '' ? '子女五' : child1_name}}-离婚证复印件一份或离婚协议书复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '5'">{{child5_name === '' ? '子女五' : child1_name}}-身份证复印件一份</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '5'">{{child5_name === '' ? '子女五' : child1_name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
      <Tag color="yellow" v-if="(type === '1' || type === '2') && child5_state === '5'">{{child5_name === '' ? '子女五' : child1_name}}-死亡证明一份</Tag>
      </Col>
    </Row>
    <Modal v-model="person_modal" title="申请人的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>姓名</Button>
        </Col>
        <Col span="20">
        <Input v-model="person_name" :placeholder="person_name === '' ? '请输入申请人的姓名' : '请输入' + person_name + '的姓名'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>证件号码</Button>
        </Col>
        <Col span="20">
        <Input v-model="person_number" :placeholder="person_name === '' ? '请输入申请人的证件号码' : '请输入' + person_name + '的证件号码'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>联系电话</Button>
        </Col>
        <Col span="20">
        <Input v-model="person_phone" :placeholder="person_name === '' ? '请输入申请人的联系电话' : '请输入' + person_name + '的联系电话'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>联系地址</Button>
        </Col>
        <Col span="20">
        <Input v-model="person_address" :placeholder="person_name === '' ? '请输入申请人的联系地址' : '请输入' + person_name + '的联系地址'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>原工作单位</Button>
        </Col>
        <Col span="20">
        <Input v-model="person_company" :placeholder="person_name === '' ? '请输入申请人的失业前所在单位' : '请输入' + person_name + '的失业前所在单位'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>失业时间</Button>
        </Col>
        <Col span="20">
        <Date-picker type="date" v-model="person_timeA" :placeholder="person_name === '' ? '请输入申请人的失业时间' : '请输入' + person_name + '的失业时间'"></Date-picker>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>登记时间</Button>
        </Col>
        <Col span="20">
        <Date-picker type="date" v-model="person_timeB" :placeholder="person_name === '' ? '请输入申请人的城镇登记失业时间' : '请输入' + person_name + '的城镇登记失业时间'"></Date-picker>
        </Col>
      </Row>
      <br>
      <div v-if="category === '1'">
        <Row type="flex" justify="center" align="middle">
          <Col span="4">
          <Button type="text" disabled>从事岗位</Button>
          </Col>
          <Col span="20">
          <Radio-group v-model="person_job" type="button">
            <Radio label="1">钟点工</Radio>
            <Radio label="2">早市、夜市摆摊经营</Radio>
            <Radio label="3">街道、社区组织的帮扶</Radio>
            <Radio label="4">家庭服务</Radio>
            <Radio label="5">家庭手工业</Radio>
            <Radio label="6">社区公益性劳动</Radio>
            <Radio label="7">其他就业帮扶</Radio>
          </Radio-group>
          </Col>
        </Row>
        <br>
      </div>
      <br>
      <Row type="flex" justify="center" align="middle" v-if="type === '1' || type === '2'">
        <Col span="4">
        <Button type="text" disabled>婚姻状况</Button>
        </Col>
        <Col span="20">
        <Radio-group v-model="person_marriage" type="button">
          <Radio label="1" v-if="type === '1' || type === '2'">未婚</Radio>
          <Radio label="2" v-if="type === '1'">已婚</Radio>
          <Radio label="3" v-if="type === '1' || type === '2'">离异</Radio>
          <Radio label="4" v-if="type === '1' || type === '2'">丧偶</Radio>
        </Radio-group>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle" v-if="type === '1' || type === '2'">
        <Col span="4">
        <Button type="text" disabled>子女数量</Button>
        </Col>
        <Col span="16">
        <Slider v-model="childNum" max="5" show-stops show-tip show-input></Slider>
        </Col>
        <Col span="4"></Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle" v-if="type === '3'">
        <Col span="4">
        <Button type="text" disabled>残疾证号</Button>
        </Col>
        <Col span="20">
        <Input v-model="person_disability" placeholder="请享受人员残疾证号"></Input>
        </Col>
      </Row>
      <br>
      <div slot="footer">
        <Button type="info" @click="resetPerson">重置</Button>
        <Button type="success" @click="person_modal = false">保存</Button>
      </div>
    </Modal>
    <Modal v-model="spouse_modal" title="配偶的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>状态</Button>
        </Col>
        <Col span="20">
        <Radio-group v-model="spouse_retire" type="button">
          <Radio label="1">未退休</Radio>
          <Radio label="2">已退休</Radio>
        </Radio-group>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>姓名</Button>
        </Col>
        <Col span="20">
        <Input v-model="spouse_name" :placeholder="spouse_name === '' ? '请输入配偶的姓名' : '请输入' + spouse_name + '的姓名'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>证件号码</Button>
        </Col>
        <Col span="20">
        <Input v-model="spouse_number" :placeholder="spouse_name === '' ? '请输入配偶的证件号码' : '请输入' + spouse_name + '的证件号码'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>工作单位</Button>
        </Col>
        <Col span="20">
        <Input v-model="spouse_company" :placeholder="spouse_name === '' ? '请输入配偶的原（现）工作（学习）单位' : '请输入' + spouse_name + '的原（现）工作（学习）单位'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>登记时间</Button>
        </Col>
        <Col span="20">
        <Date-picker type="date" v-model="person_time" :placeholder="spouse_name === '' ? '请选择配偶的城镇登记失业时间' : '请选择' + spouse_name + '的城镇登记失业时间'"></Date-picker>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>备注</Button>
        </Col>
        <Col span="20">
        <Input v-model="spouse_remark" :placeholder="spouse_name === '' ? '请输入配偶的备注' : '请输入' + spouse_name + '的备注'"></Input>
        </Col>
      </Row>
      <br>
      <div slot="footer">
        <Button type="info" @click="resetSpouse">重置</Button>
        <Button type="success" @click="spouse_modal = false">保存</Button>
      </div>
    </Modal>
    <Modal v-model="child1_modal" title="子女1的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>状态</Button>
        </Col>
        <Col span="20">
        <Radio-group v-model="child1_state" type="button">
          <Radio label="1">在校学生</Radio>
          <Radio label="2">已结婚</Radio>
          <Radio label="3">未婚</Radio>
          <Radio label="4">离异</Radio>
          <Radio label="5">丧偶</Radio>
        </Radio-group>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>姓名</Button>
        </Col>
        <Col span="20">
        <Input v-model="child1_name" :placeholder="child1_name === '' ? '请输入子女1的姓名' : '请输入' + child1_name + '的姓名'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>证件号码</Button>
        </Col>
        <Col span="20">
        <Input v-model="child1_number" :placeholder="child1_name === '' ? '请输入子女1的证件号码' : '请输入' + child1_name + '的证件号码'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>工作单位</Button>
        </Col>
        <Col span="20">
        <Input v-model="child1_company" :placeholder="child1_name === '' ? '请输入子女1的原（现）工作（学习）单位' : '请输入' + child1_name + '的原（现）工作（学习）单位'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>登记时间</Button>
        </Col>
        <Col span="20">
        <Date-picker type="date" v-model="child1_time" :placeholder="child1_name === '' ? '请选择子女1的城镇登记失业时间' : '请选择' + child1_name + '的城镇登记失业时间'"></Date-picker>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>备注</Button>
        </Col>
        <Col span="20">
        <Input v-model="child1_remark" :placeholder="child1_name === '' ? '请输入子女1的备注' : '请输入' + child1_name + '的备注'"></Input>
        </Col>
      </Row>
      <br>
      <div slot="footer">
        <Button type="info" @click="resetChild1">重置</Button>
        <Button type="success" @click="child1_modal = false">保存</Button>
      </div>
    </Modal>
    <Modal v-model="child2_modal" title="子女2的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>状态</Button>
        </Col>
        <Col span="20">
        <Radio-group v-model="child2_state" type="button">
          <Radio label="1">在校学生</Radio>
          <Radio label="2">已结婚</Radio>
          <Radio label="3">未婚</Radio>
          <Radio label="4">离异</Radio>
          <Radio label="5">丧偶</Radio>
        </Radio-group>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>姓名</Button>
        </Col>
        <Col span="20">
        <Input v-model="child2_name" :placeholder="child2_name === '' ? '请输入子女2的姓名' : '请输入' + child2_name + '的姓名'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>证件号码</Button>
        </Col>
        <Col span="20">
        <Input v-model="child2_number" :placeholder="child2_name === '' ? '请输入子女2的证件号码' : '请输入' + child2_name + '的证件号码'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>工作单位</Button>
        </Col>
        <Col span="20">
        <Input v-model="child2_company" :placeholder="child2_name === '' ? '请输入子女2的原（现）工作（学习）单位' : '请输入' + child2_name + '的原（现）工作（学习）单位'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>登记时间</Button>
        </Col>
        <Col span="20">
        <Date-picker type="date" v-model="child2_time" :placeholder="child2_name === '' ? '请选择子女2的城镇登记失业时间' : '请选择' + child2_name + '的城镇登记失业时间'"></Date-picker>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>备注</Button>
        </Col>
        <Col span="20">
        <Input v-model="child2_remark" :placeholder="child2_name === '' ? '请输入子女2的备注' : '请输入' + child2_name + '的备注'"></Input>
        </Col>
      </Row>
      <br>
      <div slot="footer">
        <Button type="info" @click="resetChild2">重置</Button>
        <Button type="success" @click="child2_modal = false">保存</Button>
      </div>
    </Modal>
    <Modal v-model="child3_modal" title="子女3的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>状态</Button>
        </Col>
        <Col span="20">
        <Radio-group v-model="child3_state" type="button">
          <Radio label="1">在校学生</Radio>
          <Radio label="2">已结婚</Radio>
          <Radio label="3">未婚</Radio>
          <Radio label="4">离异</Radio>
          <Radio label="5">丧偶</Radio>
        </Radio-group>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>姓名</Button>
        </Col>
        <Col span="20">
        <Input v-model="child3_name" :placeholder="child3_name === '' ? '请输入子女3的姓名' : '请输入' + child3_name + '的姓名'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>证件号码</Button>
        </Col>
        <Col span="20">
        <Input v-model="child3_number" :placeholder="child3_name === '' ? '请输入子女3的证件号码' : '请输入' + child3_name + '的证件号码'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>工作单位</Button>
        </Col>
        <Col span="20">
        <Input v-model="child3_company" :placeholder="child3_name === '' ? '请输入子女3的原（现）工作（学习）单位' : '请输入' + child3_name + '的原（现）工作（学习）单位'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>登记时间</Button>
        </Col>
        <Col span="20">
        <Date-picker type="date" v-model="child3_time" :placeholder="child3_name === '' ? '请选择子女3的城镇登记失业时间' : '请选择' + child3_name + '的城镇登记失业时间'"></Date-picker>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>备注</Button>
        </Col>
        <Col span="20">
        <Input v-model="child3_remark" :placeholder="child3_name === '' ? '请输入子女3的备注' : '请输入' + child3_name + '的备注'"></Input>
        </Col>
      </Row>
      <br>
      <div slot="footer">
        <Button type="info" @click="resetChild3">重置</Button>
        <Button type="success" @click="child3_modal = false">保存</Button>
      </div>
    </Modal>
    <Modal v-model="child4_modal" title="子女4的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>状态</Button>
        </Col>
        <Col span="20">
        <Radio-group v-model="child4_state" type="button">
          <Radio label="1">在校学生</Radio>
          <Radio label="2">已结婚</Radio>
          <Radio label="3">未婚</Radio>
          <Radio label="4">离异</Radio>
          <Radio label="5">丧偶</Radio>
        </Radio-group>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>姓名</Button>
        </Col>
        <Col span="20">
        <Input v-model="child4_name" :placeholder="child4_name === '' ? '请输入子女4的姓名' : '请输入' + child4_name + '的姓名'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>证件号码</Button>
        </Col>
        <Col span="20">
        <Input v-model="child4_number" :placeholder="child4_name === '' ? '请输入子女4的证件号码' : '请输入' + child4_name + '的证件号码'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>工作单位</Button>
        </Col>
        <Col span="20">
        <Input v-model="child4_company" :placeholder="child4_name === '' ? '请输入子女4的原（现）工作（学习）单位' : '请输入' + child4_name + '的原（现）工作（学习）单位'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>登记时间</Button>
        </Col>
        <Col span="20">
        <Date-picker type="date" v-model="child4_time" :placeholder="child4_name === '' ? '请选择子女4的城镇登记失业时间' : '请选择' + child4_name + '的城镇登记失业时间'"></Date-picker>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>备注</Button>
        </Col>
        <Col span="20">
        <Input v-model="child4_remark" :placeholder="child4_name === '' ? '请输入子女4的备注' : '请输入' + child4_name + '的备注'"></Input>
        </Col>
      </Row>
      <br>
      <div slot="footer">
        <Button type="info" @click="resetChild4">重置</Button>
        <Button type="success" @click="child4_modal = false">保存</Button>
      </div>
    </Modal>
    <Modal v-model="child5_modal" title="子女5的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>状态</Button>
        </Col>
        <Col span="20">
        <Radio-group v-model="child5_state" type="button">
          <Radio label="1">在校学生</Radio>
          <Radio label="2">已结婚</Radio>
          <Radio label="3">未婚</Radio>
          <Radio label="4">离异</Radio>
          <Radio label="5">丧偶</Radio>
        </Radio-group>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>姓名</Button>
        </Col>
        <Col span="20">
        <Input v-model="child5_name" :placeholder="child5_name === '' ? '请输入子女5的姓名' : '请输入' + child5_name + '的姓名'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>证件号码</Button>
        </Col>
        <Col span="20">
        <Input v-model="child5_number" :placeholder="child5_name === '' ? '请输入子女5的证件号码' : '请输入' + child5_name + '的证件号码'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>工作单位</Button>
        </Col>
        <Col span="20">
        <Input v-model="child5_company" :placeholder="child5_name === '' ? '请输入子女5的原（现）工作（学习）单位' : '请输入' + child5_name + '的原（现）工作（学习）单位'"></Input>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>登记时间</Button>
        </Col>
        <Col span="20">
        <Date-picker type="date" v-model="child5_time" :placeholder="child5_name === '' ? '请选择子女5的城镇登记失业时间' : '请选择' + child5_name + '的城镇登记失业时间'"></Date-picker>
        </Col>
      </Row>
      <br>
      <Row type="flex" justify="center" align="middle">
        <Col span="4">
        <Button type="text" disabled>备注</Button>
        </Col>
        <Col span="20">
        <Input v-model="child5_remark" :placeholder="child5_name === '' ? '请输入子女5的备注' : '请输入' + child5_name + '的备注'"></Input>
        </Col>
      </Row>
      <br>
      <div slot="footer">
        <Button type="info" @click="resetChild5">重置</Button>
        <Button type="success" @click="child5_modal = false">保存</Button>
      </div>
    </Modal>
  </div>
</template>
<script>
  export default {
    name: 'list',
    data () {
      return {
        category: '1',
        type: '1',
        person_marriage: '1',
        childNum: 0,
        person_modal: false,
        person_name: '',
        person_number: '',
        person_phone: '',
        person_address: '',
        person_company: '',
        person_timeA: '',
        person_timeB: '',
        person_job: '1',
        person_disability: '',
        child1_modal: false,
        child1_state: '',
        child1_name: '',
        child1_number: '',
        child1_company: '',
        child1_time: '',
        child1_remark: '',
        child2_modal: false,
        child2_state: '',
        child2_name: '',
        child2_number: '',
        child2_company: '',
        child2_time: '',
        child2_remark: '',
        child3_modal: false,
        child3_state: '',
        child3_name: '',
        child3_number: '',
        child3_company: '',
        child3_time: '',
        child3_remark: '',
        child4_modal: false,
        child4_state: '',
        child4_name: '',
        child4_number: '',
        child4_company: '',
        child4_time: '',
        child4_remark: '',
        child5_modal: false,
        child5_state: '',
        child5_name: '',
        child5_number: '',
        child5_company: '',
        child5_time: '',
        child5_remark: '',
        spouse_modal: false,
        spouse_name: '',
        spouse_number: '',
        spouse_company: '',
        spouse_time: '',
        spouse_remark: '',
        spouse_retire: ''
      }
    },
    methods: {
      resetPerson () {
        this.person_name = ''
        this.person_number = ''
        this.person_phone = ''
        this.person_address = ''
        this.person_company = ''
        this.person_timeA = ''
        this.person_timeB = ''
        this.person_job = '1'
        this.person_marriage = '1'
      },
      resetSpouse () {
        this.spouse_retire = ''
        this.spouse_name = ''
        this.spouse_number = ''
        this.spouse_company = ''
        this.spouse_time = ''
        this.spouse_remark = ''
      },
      resetChild1 () {
        this.child1_state = ''
        this.child1_name = ''
        this.child1_number = ''
        this.child1_company = ''
        this.child1_time = ''
        this.child1_remark = ''
      },
      resetChild2 () {
        this.child2_state = ''
        this.child2_name = ''
        this.child2_number = ''
        this.child2_company = ''
        this.child2_time = ''
        this.child2_remark = ''
      },
      resetChild3 () {
        this.child3_state = ''
        this.child3_name = ''
        this.child3_number = ''
        this.child3_company = ''
        this.child3_time = ''
        this.child3_remark = ''
      },
      resetChild4 () {
        this.child4_state = ''
        this.child4_name = ''
        this.child4_number = ''
        this.child4_company = ''
        this.child4_time = ''
        this.child4_remark = ''
      },
      resetChild5 () {
        this.child5_state = ''
        this.child5_name = ''
        this.child5_number = ''
        this.child5_company = ''
        this.child5_time = ''
        this.child5_remark = ''
      },
      resetChild (value) {

      }
    }
  }
</script>
