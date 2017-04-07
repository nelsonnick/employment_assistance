<template>
  <div>
    <Row type="flex" justify="center" align="middle">
      <Col span="2">
        <Button type="text" disabled>享受类别</Button>
      </Col>
      <Col span="22">
        <Radio-group v-model="type" type="button">
          <Radio label="1">零就业家庭</Radio>
          <Radio label="2">单亲抚养未成年</Radio>
          <Radio label="3">低保</Radio>
          <Radio label="4">残疾</Radio>
          <Radio label="5">困难高校毕业生</Radio>
          <Radio label="6">成年孤儿</Radio>
        </Radio-group>
      </Col>
    </Row>
    <br>
    <Row type="flex" justify="center" align="middle">
      <Col span="2">
        <Button type="text" disabled>婚姻状况</Button>
      </Col>
      <Col span="22">
        <Radio-group v-model="marriage" type="button">
          <Radio label="1">未婚</Radio>
          <Radio label="2">已婚</Radio>
          <Radio label="3">离异</Radio>
          <Radio label="4">丧偶</Radio>
        </Radio-group>
      </Col>
    </Row>
    <br>
    <Row type="flex" justify="center" align="middle">
      <Col span="2">
        <Button type="text" disabled>退休情况</Button>
      </Col>
      <Col span="22">
        <Radio-group v-model="retire" type="button">
          <Radio label="1">未退休</Radio>
          <Radio label="2">已退休</Radio>
        </Radio-group>
      </Col>
    </Row>
    <br>
    <Row type="flex" justify="center" align="middle">
      <Col span="2">
        <Button type="text" disabled>子女数量</Button>
      </Col>
      <Col span="8">
        <Slider v-model="childNum" max="5" show-stops show-tip></Slider>
      </Col>
      <Col span="14"></Col>
    </Row>
    <br>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        type: '',
        marriage: '',
        childNum: 1,
        retire: ''
      }
    }
  }
</script>
