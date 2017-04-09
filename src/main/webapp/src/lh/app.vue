<template>
  <div>
    <Form :label-width="80">
      <Form-item label="申请类别" required>
        <Radio-group v-model="category" type="button">
          <Radio label="1">灵活就业人员</Radio>
          <Radio label="2">公益性岗位</Radio>
          <Radio label="3">企业吸纳</Radio>
        </Radio-group>
      </Form-item>
      <Form-item label="申请条件" required>
        <Radio-group v-model="type" type="button">
          <Radio label="1">城镇零就业家庭的4050失业人员</Radio>
          <Radio label="2">抚养未成年子女单亲家庭成员的4050失业人员</Radio>
          <Radio label="3">享受城市居民最低生活保障的4050失业人员</Radio>
          <Radio label="4">持有《中华人民共和国残疾人证》的4050失业人员</Radio>
          <Radio label="7" v-if="category === '3'">4050失业人员且连续失业半年以上</Radio>
          <Radio label="8" v-if="category === '3'">抚养未成年子女单亲家庭成员的失业人员</Radio>
          <Radio label="9" v-if="category === '3'">享受城市居民最低生活保障的失业人员</Radio>
          <Radio label="10" v-if="category === '3'">持有《中华人民共和国残疾人证》的失业人员</Radio>
          <Radio label="5">特困家庭未就业的高校毕业生</Radio>
          <Radio label="6">城镇登记失业的成年后孤儿</Radio>
        </Radio-group>
      </Form-item>
      <Form-item label="家庭成员" required>
        <Button shape="circle" type="info"  @click="person.modal = true">{{person.name === '' ? '申请人' : person.name}}</Button>
        <Button shape="circle" type="success" v-if="type === '1' && person.marriage === '2'" @click="spouse.modal = true">{{spouse.name === '' ? '配偶' : spouse.name}}</Button>
        <Button shape="circle" type="warning" v-if="(type === '1' || type === '2' || type === '8') && (person.childNum == 1 || person.childNum == 2 || person.childNum == 3 || person.childNum == 4 || person.childNum == 5)" @click="child1.modal = true">{{child1.name === '' ? '子女1' : child1.name}}</Button>
        <Button shape="circle" type="warning" v-if="(type === '1' || type === '2' || type === '8') && (person.childNum == 2 || person.childNum == 3 || person.childNum == 4 || person.childNum == 5)" @click="child2.modal = true">{{child2.name === '' ? '子女2' : child2.name}}</Button>
        <Button shape="circle" type="warning" v-if="(type === '1' || type === '2' || type === '8') && (person.childNum == 3 || person.childNum == 4 || person.childNum == 5)" @click="child3.modal = true">{{child3.name === '' ? '子女3' : child3.name}}</Button>
        <Button shape="circle" type="warning" v-if="(type === '1' || type === '2' || type === '8') && (person.childNum == 4 || person.childNum == 5)" @click="child4.modal = true">{{child4.name === '' ? '子女4' : child4.name}}</Button>
        <Button shape="circle" type="warning" v-if="(type === '1' || type === '2' || type === '8') && person.childNum == 5" @click="child5.modal = true">{{child5.name === '' ? '子女5' : child5.name}}</Button>
      </Form-item>
      <Form-item label="留存材料" required>
        <Tag color="blue">{{person.name === '' ? '本人' : person.name}}-身份证复印件一份</Tag>
        <Tag color="blue">{{person.name === '' ? '本人' : person.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="blue">{{person.name === '' ? '本人' : person.name}}-城镇登记失业证明一份</Tag>
        <Tag color="blue" v-if="type === '3' || type === '9'">{{person.name === '' ? '本人' : person.name}}-《城市居民最低生活保障证》复印件一份</Tag>
        <Tag color="blue" v-if="type === '3' || type === '9'">{{person.name === '' ? '本人' : person.name}}-《城市居民最低生活保障证》登记中的家庭成员户口簿复印件一份</Tag>
        <Tag color="blue" v-if="type === '4'  || type === '10'">{{person.name === '' ? '本人' : person.name}}-《中华人民共和国残疾人证》复印件一份</Tag>
        <Tag color="blue" v-if="type === '5'">{{person.name === '' ? '本人' : person.name}}-《城市居民最低生活保障证》复印件一份</Tag>
        <Tag color="blue" v-if="type === '5'">{{person.name === '' ? '本人' : person.name}}-《济南市特困职工优待证》复印件一份</Tag>
        <Tag color="blue" v-if="type === '5'">{{person.name === '' ? '本人' : person.name}}-《毕业证》复印件一份</Tag>
        <Tag color="blue" v-if="type === '5'">{{person.name === '' ? '本人' : person.name}}-《报到证》复印件一份</Tag>
        <Tag color="blue" v-if="type === '6'">{{person.name === '' ? '本人' : person.name}}-县级以上民政部门认定的孤儿证明复印件一份</Tag>
        <Tag color="blue" v-if="person.marriage === '1'">{{person.name === '' ? '本人' : person.name}}-单身证明</Tag>
        <Tag color="green" v-if="type === '1' && person.marriage === '2'">{{spouse.name === '' ? '配偶' : spouse.name}}-身份证复印件一份</Tag>
        <Tag color="green" v-if="type === '1' && person.marriage === '2'">{{spouse.name === '' ? '配偶' : spouse.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="green" v-if="type === '1' && person.marriage === '2' && spouse.state === '1'">{{spouse.name === '' ? '配偶' : spouse.name}}-城镇登记失业证明一份</Tag>
        <Tag color="green" v-if="type === '1' && person.marriage === '2' && spouse.state === '2'">{{spouse.name === '' ? '配偶' : spouse.name}}-退休证复印件一份</Tag>
        <Tag color="green" v-if="(type === '1' || type === '2' || type === '8') && person.marriage === '3'">{{person.name === '' ? '本人' : person.name}}-离婚证复印件一份或离婚协议书复印件一份</Tag>
        <Tag color="green" v-if="(type === '1' || type === '2' || type === '8') && person.marriage === '4'">{{spouse.name === '' ? '配偶' : spouse.name}}-死亡证明一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && person.marriage === '1' && (person.childNum == 1 || person.childNum == 2 || person.childNum == 3 || person.childNum == 4 || person.childNum == 5)">{{child1.name === '' ? '子女一' : child1.name}}-抚养证明</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '1'">{{child1.name === '' ? '子女一' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '1'">{{child1.name === '' ? '子女一' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '1'">{{child1.name === '' ? '子女一' : child1.name}}-在校证明</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '2'">{{child1.name === '' ? '子女一' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '2'">{{child1.name === '' ? '子女一' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '2'">{{child1.name === '' ? '子女一' : child1.name}}-结婚证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '3'">{{child1.name === '' ? '子女一' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '3'">{{child1.name === '' ? '子女一' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '3'">{{child1.name === '' ? '子女一' : child1.name}}-城镇登记失业证明一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '4'">{{child1.name === '' ? '子女一' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '4'">{{child1.name === '' ? '子女一' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '4'">{{child1.name === '' ? '子女一' : child1.name}}-离婚证复印件一份或离婚协议书复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '5'">{{child1.name === '' ? '子女一' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '5'">{{child1.name === '' ? '子女一' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child1.state === '5'">{{child1.name === '' ? '子女一' : child1.name}}-死亡证明一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && person.marriage === '1' && (person.childNum == 2 || person.childNum == 3 || person.childNum == 4 || person.childNum == 5)">{{child2.name === '' ? '子女二' : child2.name}}-抚养证明</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '1'">{{child2.name === '' ? '子女二' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '1'">{{child2.name === '' ? '子女二' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '1'">{{child2.name === '' ? '子女二' : child1.name}}-在校证明</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '2'">{{child2.name === '' ? '子女二' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '2'">{{child2.name === '' ? '子女二' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '2'">{{child2.name === '' ? '子女二' : child1.name}}-结婚证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '3'">{{child2.name === '' ? '子女二' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '3'">{{child2.name === '' ? '子女二' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '3'">{{child2.name === '' ? '子女二' : child1.name}}-城镇登记失业证明一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '4'">{{child2.name === '' ? '子女二' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '4'">{{child2.name === '' ? '子女二' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '4'">{{child2.name === '' ? '子女二' : child1.name}}-离婚证复印件一份或离婚协议书复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '5'">{{child2.name === '' ? '子女二' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '5'">{{child2.name === '' ? '子女二' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child2.state === '5'">{{child2.name === '' ? '子女二' : child1.name}}-死亡证明一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && person.marriage === '1' && (person.childNum == 3 || person.childNum == 4 || person.childNum == 5)">{{child3.name === '' ? '子女三' : child3.name}}-抚养证明</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '1'">{{child3.name === '' ? '子女三' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '1'">{{child3.name === '' ? '子女三' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '1'">{{child3.name === '' ? '子女三' : child1.name}}-在校证明</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '2'">{{child3.name === '' ? '子女三' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '2'">{{child3.name === '' ? '子女三' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '2'">{{child3.name === '' ? '子女三' : child1.name}}-结婚证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '3'">{{child3.name === '' ? '子女三' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '3'">{{child3.name === '' ? '子女三' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '3'">{{child3.name === '' ? '子女三' : child1.name}}-城镇登记失业证明一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '4'">{{child3.name === '' ? '子女三' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '4'">{{child3.name === '' ? '子女三' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '4'">{{child3.name === '' ? '子女三' : child1.name}}-离婚证复印件一份或离婚协议书复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '5'">{{child3.name === '' ? '子女三' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '5'">{{child3.name === '' ? '子女三' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child3.state === '5'">{{child3.name === '' ? '子女三' : child1.name}}-死亡证明一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && person.marriage === '1' && (person.childNum == 4 || person.childNum == 5)">{{child4.name === '' ? '子女四' : child4.name}}-抚养证明</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '1'">{{child4.name === '' ? '子女四' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '1'">{{child4.name === '' ? '子女四' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '1'">{{child4.name === '' ? '子女四' : child1.name}}-在校证明</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '2'">{{child4.name === '' ? '子女四' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '2'">{{child4.name === '' ? '子女四' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '2'">{{child4.name === '' ? '子女四' : child1.name}}-结婚证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '3'">{{child4.name === '' ? '子女四' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '3'">{{child4.name === '' ? '子女四' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '3'">{{child4.name === '' ? '子女四' : child1.name}}-城镇登记失业证明一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '4'">{{child4.name === '' ? '子女四' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '4'">{{child4.name === '' ? '子女四' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '4'">{{child4.name === '' ? '子女四' : child1.name}}-离婚证复印件一份或离婚协议书复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '5'">{{child4.name === '' ? '子女四' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '5'">{{child4.name === '' ? '子女四' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child4.state === '5'">{{child4.name === '' ? '子女四' : child1.name}}-死亡证明一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && person.marriage === '1' && person.childNum == 5">{{child5.name === '' ? '子女五' : child5.name}}-抚养证明</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '1'">{{child5.name === '' ? '子女五' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '1'">{{child5.name === '' ? '子女五' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '1'">{{child5.name === '' ? '子女五' : child1.name}}-在校证明</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '2'">{{child5.name === '' ? '子女五' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '2'">{{child5.name === '' ? '子女五' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '2'">{{child5.name === '' ? '子女五' : child1.name}}-结婚证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '3'">{{child5.name === '' ? '子女五' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '3'">{{child5.name === '' ? '子女五' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '3'">{{child5.name === '' ? '子女五' : child1.name}}-城镇登记失业证明一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '4'">{{child5.name === '' ? '子女五' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '4'">{{child5.name === '' ? '子女五' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '4'">{{child5.name === '' ? '子女五' : child1.name}}-离婚证复印件一份或离婚协议书复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '5'">{{child5.name === '' ? '子女五' : child1.name}}-身份证复印件一份</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '5'">{{child5.name === '' ? '子女五' : child1.name}}-户口本复印件一份（户主页、索引页、个人单页）</Tag>
        <Tag color="yellow" v-if="(type === '1' || type === '2' || type === '8') && child5.state === '5'">{{child5.name === '' ? '子女五' : child1.name}}-死亡证明一份</Tag>
      </Form-item>
    </Form>
    <Modal v-model="person.modal" title="申请人的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Form :model="person" :rules="person_validate" :label-width="80">
        <Form-item label="姓名" prop="name">
          <Input v-model="person.name" :placeholder="person.name === '' ? '请输入申请人的姓名' : '请输入' + person.name + '的姓名'"></Input>
        </Form-item>
        <Form-item label="证件号码" prop="number">
          <Input v-model="person.number" :placeholder="person.name === '' ? '请输入申请人的证件号码' : '请输入' + person.name + '的证件号码'"></Input>
        </Form-item>
        <Form-item label="联系电话" prop="phone">
          <Input v-model="person.phone" :placeholder="person.name === '' ? '请输入申请人的联系电话' : '请输入' + person.name + '的联系电话'"></Input>
        </Form-item>
        <Form-item label="联系地址" prop="address">
          <Input v-model="person.address" :placeholder="person.name === '' ? '请输入申请人的联系地址' : '请输入' + person.name + '的联系地址'"></Input>
        </Form-item>
        <Form-item label="原工作单位" prop="company">
          <Input v-model="person.company" :placeholder="person.name === '' ? '请输入申请人的失业前所在单位' : '请输入' + person.name + '的失业前所在单位'"></Input>
        </Form-item>
        <Form-item label="失业时间" prop="timeA">
          <Date-picker type="date" v-model="person.timeA" :placeholder="person.name === '' ? '请选择申请人的失业时间' : '请输入' + person.name + '的失业时间'"></Date-picker>
        </Form-item>
        <Form-item label="登记时间" prop="timeB">
          <Date-picker type="date" v-model="person.timeB" :placeholder="person.name === '' ? '请选择申请人的城镇登记失业时间' : '请输入' + person.name + '的城镇登记失业时间'"></Date-picker>
        </Form-item>
        <Form-item label="从事岗位" v-if="category === '1'">
          <Radio-group v-model="person.job" type="button">
            <Radio label="1">钟点工</Radio>
            <Radio label="2">早市、夜市摆摊经营</Radio>
            <Radio label="3">街道、社区组织的帮扶</Radio>
            <Radio label="4">家庭服务</Radio>
            <Radio label="5">家庭手工业</Radio>
            <Radio label="6">社区公益性劳动</Radio>
            <Radio label="7">其他就业帮扶</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="婚姻状况" v-if="type === '1' || type === '2'">
          <Radio-group v-model="person.marriage" type="button">
            <Radio label="1" v-if="type === '1' || type === '2'">未婚</Radio>
            <Radio label="2" v-if="type === '1'">已婚</Radio>
            <Radio label="3" v-if="type === '1' || type === '2'">离异</Radio>
            <Radio label="4" v-if="type === '1' || type === '2'">丧偶</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="子女数量" v-if="type === '1' || type === '2'">
          <Slider v-model="person.childNum" max="5" show-stops show-tip show-input></Slider>
        </Form-item>
        <Form-item label="残疾证号" v-if="type === '3' || type === '10'">
          <Input v-model="person.disability" placeholder="请享受人员残疾证号"></Input>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="error" @click="person.modal = false">关闭</Button>
        <Button type="info" @click="resetPerson">重置</Button>
        <Button type="success" @click="person.modal = false">保存</Button>
      </div>
    </Modal>
    <Modal v-model="spouse.modal" title="配偶的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Form :model="spouse" :rules="family_validate" :label-width="80">
        <Form-item label="状态" prop="state">
          <Radio-group v-model="spouse.state" type="button">
            <Radio label="1">未退休</Radio>
            <Radio label="2">已退休</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="姓名" prop="name">
          <Input v-model="spouse.name" :placeholder="spouse.name === '' ? '请输入配偶的姓名' : '请输入' + spouse.name + '的姓名'"></Input>
        </Form-item>
        <Form-item label="证件号码" prop="number">
          <Input v-model="spouse.number" :placeholder="spouse.name === '' ? '请输入配偶的证件号码' : '请输入' + spouse.name + '的证件号码'"></Input>
        </Form-item>
        <Form-item label="工作单位" prop="company">
          <Input v-model="spouse.company" :placeholder="spouse.name === '' ? '请输入配偶的原（现）工作（学习）单位' : '请输入' + spouse.name + '的原（现）工作（学习）单位'"></Input>
        </Form-item>
        <Form-item label="登记时间" prop="time">
          <Date-picker type="date" v-model="spouse.time" :placeholder="spouse.name === '' ? '请选择配偶的城镇登记失业时间' : '请选择' + spouse.name + '的城镇登记失业时间'"></Date-picker>
        </Form-item>
        <Form-item label="备注">
          <Input v-model="spouse.remark" :placeholder="spouse.name === '' ? '请输入配偶的备注' : '请输入' + spouse.name + '的备注'"></Input>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="error" @click="spouse.modal = false">关闭</Button>
        <Button type="info" @click="resetSpouse">重置</Button>
        <Button type="success" @click="spouse.modal = false">保存</Button>
      </div>
    </Modal>
    <Modal v-model="child1.modal" title="子女1的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Form :model="child1" :rules="family_validate" :label-width="80">
        <Form-item label="状态" prop="state">
          <Radio-group v-model="child1.state" type="button">
            <Radio label="1">在校学生</Radio>
            <Radio label="2">已结婚</Radio>
            <Radio label="3">未婚</Radio>
            <Radio label="4">离异</Radio>
            <Radio label="5">丧偶</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="姓名" prop="name">
          <Input v-model="child1.name" :placeholder="child1.name === '' ? '请输入子女1的姓名' : '请输入' + child1.name + '的姓名'"></Input>
        </Form-item>
        <Form-item label="证件号码" prop="number">
          <Input v-model="child1.number" :placeholder="child1.name === '' ? '请输入子女1的证件号码' : '请输入' + child1.name + '的证件号码'"></Input>
        </Form-item>
        <Form-item label="工作单位" prop="company">
          <Input v-model="child1.company" :placeholder="child1.name === '' ? '请输入子女1的原（现）工作（学习）单位' : '请输入' + child1.name + '的原（现）工作（学习）单位'"></Input>
        </Form-item>
        <Form-item label="登记时间" prop="time">
          <Date-picker type="date" v-model="child1.time" :placeholder="child1.name === '' ? '请选择子女1的城镇登记失业时间' : '请选择' + child1.name + '的城镇登记失业时间'"></Date-picker>
        </Form-item>
        <Form-item label="备注">
          <Input v-model="child1.remark" :placeholder="child1.name === '' ? '请输入子女1的备注' : '请输入' + child1.name + '的备注'"></Input>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="error" @click="child1.modal = false">关闭</Button>
        <Button type="info" @click="resetChild1">重置</Button>
        <Button type="success" @click="child1.modal = false">保存</Button>
      </div>
    </Modal>
    <Modal v-model="child2.modal" title="子女2的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Form :model="child2" :rules="family_validate" :label-width="80">
        <Form-item label="状态" prop="state">
          <Radio-group v-model="child2.state" type="button">
            <Radio label="1">在校学生</Radio>
            <Radio label="2">已结婚</Radio>
            <Radio label="3">未婚</Radio>
            <Radio label="4">离异</Radio>
            <Radio label="5">丧偶</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="姓名" prop="name">
          <Input v-model="child2.name" :placeholder="child2.name === '' ? '请输入子女2的姓名' : '请输入' + child2.name + '的姓名'"></Input>
        </Form-item>
        <Form-item label="证件号码" prop="number">
          <Input v-model="child2.number" :placeholder="child2.name === '' ? '请输入子女2的证件号码' : '请输入' + child2.name + '的证件号码'"></Input>
        </Form-item>
        <Form-item label="工作单位" prop="company">
          <Input v-model="child2.company" :placeholder="child2.name === '' ? '请输入子女2的原（现）工作（学习）单位' : '请输入' + child2.name + '的原（现）工作（学习）单位'"></Input>
        </Form-item>
        <Form-item label="登记时间" prop="time">
          <Date-picker type="date" v-model="child2.time" :placeholder="child2.name === '' ? '请选择子女2的城镇登记失业时间' : '请选择' + child2.name + '的城镇登记失业时间'"></Date-picker>
        </Form-item>
        <Form-item label="备注">
          <Input v-model="child2.remark" :placeholder="child2.name === '' ? '请输入子女2的备注' : '请输入' + child2.name + '的备注'"></Input>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="error" @click="child2.modal = false">关闭</Button>
        <Button type="info" @click="resetChild2">重置</Button>
        <Button type="success" @click="child2.modal = false">保存</Button>
      </div>
    </Modal>
    <Modal v-model="child3.modal" title="子女3的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Form :model="child3" :rules="family_validate" :label-width="80">
        <Form-item label="状态" prop="state">
          <Radio-group v-model="child3.state" type="button">
            <Radio label="1">在校学生</Radio>
            <Radio label="2">已结婚</Radio>
            <Radio label="3">未婚</Radio>
            <Radio label="4">离异</Radio>
            <Radio label="5">丧偶</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="姓名" prop="name">
          <Input v-model="child3.name" :placeholder="child3.name === '' ? '请输入子女3的姓名' : '请输入' + child3.name + '的姓名'"></Input>
        </Form-item>
        <Form-item label="证件号码" prop="number">
          <Input v-model="child3.number" :placeholder="child3.name === '' ? '请输入子女3的证件号码' : '请输入' + child3.name + '的证件号码'"></Input>
        </Form-item>
        <Form-item label="工作单位" prop="company">
          <Input v-model="child3.company" :placeholder="child3.name === '' ? '请输入子女3的原（现）工作（学习）单位' : '请输入' + child3.name + '的原（现）工作（学习）单位'"></Input>
        </Form-item>
        <Form-item label="登记时间" prop="time">
          <Date-picker type="date" v-model="child3.time" :placeholder="child3.name === '' ? '请选择子女3的城镇登记失业时间' : '请选择' + child3.name + '的城镇登记失业时间'"></Date-picker>
        </Form-item>
        <Form-item label="备注">
          <Input v-model="child3.remark" :placeholder="child3.name === '' ? '请输入子女3的备注' : '请输入' + child3.name + '的备注'"></Input>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="error" @click="child3.modal = false">关闭</Button>
        <Button type="info" @click="resetChild3">重置</Button>
        <Button type="success" @click="child3.modal = false">保存</Button>
      </div>
    </Modal>
    <Modal v-model="child4.modal" title="子女4的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Form :model="child4" :rules="family_validate" :label-width="80">
        <Form-item label="状态" prop="state">
          <Radio-group v-model="child4.state" type="button">
            <Radio label="1">在校学生</Radio>
            <Radio label="2">已结婚</Radio>
            <Radio label="3">未婚</Radio>
            <Radio label="4">离异</Radio>
            <Radio label="5">丧偶</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="姓名" prop="name">
          <Input v-model="child4.name" :placeholder="child4.name === '' ? '请输入子女4的姓名' : '请输入' + child4.name + '的姓名'"></Input>
        </Form-item>
        <Form-item label="证件号码" prop="number">
          <Input v-model="child4.number" :placeholder="child4.name === '' ? '请输入子女4的证件号码' : '请输入' + child4.name + '的证件号码'"></Input>
        </Form-item>
        <Form-item label="工作单位" prop="company">
          <Input v-model="child4.company" :placeholder="child4.name === '' ? '请输入子女4的原（现）工作（学习）单位' : '请输入' + child4.name + '的原（现）工作（学习）单位'"></Input>
        </Form-item>
        <Form-item label="登记时间" prop="time">
          <Date-picker type="date" v-model="child4.time" :placeholder="child4.name === '' ? '请选择子女4的城镇登记失业时间' : '请选择' + child4.name + '的城镇登记失业时间'"></Date-picker>
        </Form-item>
        <Form-item label="备注">
          <Input v-model="child4.remark" :placeholder="child4.name === '' ? '请输入子女4的备注' : '请输入' + child4.name + '的备注'"></Input>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="error" @click="child4.modal = false">关闭</Button>
        <Button type="info" @click="resetChild4">重置</Button>
        <Button type="success" @click="child4.modal = false">保存</Button>
      </div>
    </Modal>
    <Modal v-model="child5.modal" title="子女5的信息" :styles="{top: '20px'}" :closable="false" :mask-closable="false">
      <Form :model="child5" :rules="family_validate" :label-width="80">
        <Form-item label="状态" prop="state">
          <Radio-group v-model="child5.state" type="button">
            <Radio label="1">在校学生</Radio>
            <Radio label="2">已结婚</Radio>
            <Radio label="3">未婚</Radio>
            <Radio label="4">离异</Radio>
            <Radio label="5">丧偶</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="姓名" prop="name">
          <Input v-model="child5.name" :placeholder="child5.name === '' ? '请输入子女5的姓名' : '请输入' + child5.name + '的姓名'"></Input>
        </Form-item>
        <Form-item label="证件号码" prop="number">
          <Input v-model="child5.number" :placeholder="child5.name === '' ? '请输入子女5的证件号码' : '请输入' + child5.name + '的证件号码'"></Input>
        </Form-item>
        <Form-item label="工作单位" prop="company">
          <Input v-model="child5.company" :placeholder="child5.name === '' ? '请输入子女5的原（现）工作（学习）单位' : '请输入' + child5.name + '的原（现）工作（学习）单位'"></Input>
        </Form-item>
        <Form-item label="登记时间" prop="time">
          <Date-picker type="date" v-model="child5.time" :placeholder="child5.name === '' ? '请选择子女5的城镇登记失业时间' : '请选择' + child5.name + '的城镇登记失业时间'"></Date-picker>
        </Form-item>
        <Form-item label="备注">
          <Input v-model="child5.remark" :placeholder="child5.name === '' ? '请输入子女5的备注' : '请输入' + child5.name + '的备注'"></Input>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="error" @click="child5.modal = false">关闭</Button>
        <Button type="info" @click="resetChild5">重置</Button>
        <Button type="success" @click="child5.modal = false">保存</Button>
      </div>
    </Modal>
  </div>
</template>
<script>
  export default {
    name: 'list',
    data () {
      const IDNumberCheck = (rule, value, callback) => {
        if (!value || !/^\d{17}(\d|X)$/i.test(value)) {
          callback(new Error('格式错误'))
        } else {
          value = value.split('')
          let factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ]
          let parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ]
          let sum = 0
          let ai = 0
          let wi = 0
          for (let i = 0; i < 17; i++) {
            ai = value[i]
            wi = factor[i]
            sum += ai * wi
          }
          if (parity[sum % 11].toString() !== value[17].toString()) {
            callback(new Error('校验码错误'))
          }
        }
      }
      return {
        person_validate: {
          name: [
            { required: true, message: '姓名不能为空', trigger: 'change' },
            { max: 6, message: '姓名不能多于6个字符', trigger: 'change' },
            { min: 2, message: '姓名不能少于2个字符', trigger: 'change' }
          ],
          number: [
            { required: true, message: '证件号码不能为空', trigger: 'change' },
            { validator: IDNumberCheck, trigger: 'change' }
          ],
          phone: [
            { required: true, message: '联系电话不能为空', trigger: 'change' },
            { message: '请输入11位手机号码', trigger: 'change', pattern: /^\d{11}$/ }
          ],
          address: [
            { required: true, message: '联系地址不能为空', trigger: 'change' },
            { min: 2, message: '联系地址不能少于2个字符', trigger: 'change' }
          ],
          company: [
            { required: true, message: '原工作单位不能为空', trigger: 'change' },
            { min: 2, message: '原工作单位不能少于2个字符', trigger: 'change' }
          ],
          timeA: [
            { required: true, type: 'date', message: '请选择失业时间', trigger: 'change' }
          ],
          timeB: [
            { required: true, type: 'date', message: '请选择城镇登记失业时间', trigger: 'change' }
          ]
        },
        family_validate: {
          name: [
            { required: true, message: '姓名不能为空', trigger: 'change' },
            { max: 6, message: '姓名不能多于6个字符', trigger: 'change' },
            { min: 2, message: '姓名不能少于2个字符', trigger: 'change' }
          ],
          number: [
            { required: true, message: '证件号码不能为空', trigger: 'change' },
            { validator: IDNumberCheck, trigger: 'change' }
          ],
          company: [
            { required: true, message: '原工作单位不能为空', trigger: 'change' },
            { min: 2, message: '原工作单位不能少于2个字符', trigger: 'change' }
          ],
          time: [
            { required: true, type: 'date', message: '请选择城镇登记失业时间', trigger: 'change' }
          ],
          state: [
            { required: true, message: '请选择状态', trigger: 'change' }
          ]
        },
        category: '1',
        type: '1',
        person: {
          marriage: '1',
          modal: false,
          name: '',
          number: '',
          phone: '',
          address: '',
          company: '',
          timeA: '',
          timeB: '',
          job: '1',
          disability: '',
          childNum: 0
        },
        child1: {
          modal: false,
          state: '',
          name: '',
          number: '',
          company: '',
          time: '',
          remark: ''
        },
        child2: {
          modal: false,
          state: '',
          name: '',
          number: '',
          company: '',
          time: '',
          remark: ''
        },
        child3: {
          modal: false,
          state: '',
          name: '',
          number: '',
          company: '',
          time: '',
          remark: ''
        },
        child4: {
          modal: false,
          state: '',
          name: '',
          number: '',
          company: '',
          time: '',
          remark: ''
        },
        child5: {
          modal: false,
          state: '',
          name: '',
          number: '',
          company: '',
          time: '',
          remark: ''
        },
        spouse: {
          modal: false,
          state: '',
          name: '',
          number: '',
          company: '',
          time: '',
          remark: ''

        }
      }
    },
    methods: {
      resetPerson () {
        this.person.name = ''
        this.person.number = ''
        this.person.phone = ''
        this.person.address = ''
        this.person.company = ''
        this.person.timeA = ''
        this.person.timeB = ''
        this.person.job = '1'
        this.person.marriage = '1'
      },
      resetSpouse () {
        this.spouse.state = ''
        this.spouse.name = ''
        this.spouse.number = ''
        this.spouse.company = ''
        this.spouse.time = ''
        this.spouse.remark = ''
      },
      resetChild1 () {
        this.child1.state = ''
        this.child1.name = ''
        this.child1.number = ''
        this.child1.company = ''
        this.child1.time = ''
        this.child1.remark = ''
      },
      resetChild2 () {
        this.child2.state = ''
        this.child2.name = ''
        this.child2.number = ''
        this.child2.company = ''
        this.child2.time = ''
        this.child2.remark = ''
      },
      resetChild3 () {
        this.child3.state = ''
        this.child3.name = ''
        this.child3.number = ''
        this.child3.company = ''
        this.child3.time = ''
        this.child3.remark = ''
      },
      resetChild4 () {
        this.child4.state = ''
        this.child4.name = ''
        this.child4.number = ''
        this.child4.company = ''
        this.child4.time = ''
        this.child4.remark = ''
      },
      resetChild5 () {
        this.child5.state = ''
        this.child5.name = ''
        this.child5.number = ''
        this.child5.company = ''
        this.child5.time = ''
        this.child5.remark = ''
      },
      resetChild (value) {

      }
    }
  }
</script>
