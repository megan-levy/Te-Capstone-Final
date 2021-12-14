<template>
  <router-link
    tag="a"
    class="group-list-item"
    v-bind:to="{ name: itemBindLinkTo, params: params }"
    v-on:click="logData"
  >
    <div v-if="itemType === 'group'" class="top-line">
      <h1>{{ itemTitle }}</h1>
      |
      <span class="date">
        {{ itemDate }}
      </span>
    </div>
    <div v-else class="top-line">
      <h1>{{ itemTitle }}</h1>
    </div>

    <p>{{ itemDescription }}</p>

    <div v-if="itemType === 'list'">
      <p>Number of Items: {{ itemCount }}</p>
    </div>
  </router-link>
</template>

<script>
export default {
  props: ["id", "type", "title", "description", "date", "item_count"],
  data() {
    return {
      itemId: this.$props.id,
      itemType: this.$props.type,
      itemBindLinkTo: "",
      params: {},
      itemTitle: this.$props.title,
      itemDescription: this.$props.description,
      itemDate: this.$props.date,
      itemCount: this.$props.item_count,
    };
  },
  mounted() {
    switch (this.$props.type) {
      case "list":
        this.itemBindLinkTo = "shopping-list";
        this.params = {
          listId: `${this.itemId}`,
        };
        break;
      case "group":
        this.itemBindLinkTo = "group-home";
        this.params = {
          groupId: `${this.itemId}`,
        };
        break;
    }
  },
  methods: {
    logData() {
      console.log(this.itemParams);
    },
  },
};
</script>
<style scoped>
.top-line {
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  width: fit-content;
  align-items: center;
}
.date {
  padding: 10px;
}
</style>