<template>
  <div class="groups" v-if="$store.state.group">
    <div class="title-add">
      <h1>{{ $store.state.group.name }}</h1>
      <p>{{ $store.state.group.groupDescription }}</p>

      <router-link
        class="addBtn"
        v-bind:to="{ name: 'new-list' }"
        v-if="$store.state.token != ''"
        >+</router-link
      >
    </div>
    <hr />
    <br>
    <router-link
      id="return"
      tag="a"
      class="helper-link"
      :to="{ name: 'groups' }"
    >
      Return to My Groups
    </router-link>
    <div class="items-container">
      <ul class="vertical-list" id="lists-listed">
        <list-card
          v-for="list in $store.state.lists"
          :key="list.listId"
          v-bind:id="list.listId"
          v-bind:type="'list'"
          v-bind:title="list.listName"
          v-bind:description="list.listDescription"
          v-bind:date="null"
          v-bind:item_count="list.itemCount"
        />
      </ul>
    </div>
  </div>
</template>

<script>
import ListCard from "../components/ui/ListCard.vue";
import GroupService from "@/services/GroupService.js";
import ShoppingListService from "@/services/ShoppingListService.js";

export default {
  name: "groups",
  components: {
    ListCard,
  },
  mounted() {
    this.displayGroupLists(this.$route.params.groupId);
    this.getGroup(this.$route.params.groupId);
  },
  methods: {
    displayGroupLists() {
      ShoppingListService.list(this.$route.params.groupId).then((lists) => {
        console.log(lists);
        this.$store.commit("SET_LISTS", lists.data);
      });
    },
    getGroup(groupId) {
      GroupService.getSingle(groupId).then((group) => {
        this.$store.commit("SET_GROUP", group.data);
      });
    },
  },
};
</script>
<style>
li {
  text-decoration: none;
}

#list-listed {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  width: 100%;
  padding-inline-start: 0px;
}

h1 {
  margin-top: 1px;
}
.items-container {
  overflow-y: scroll;
  height: 100%;
}
.groups {
  height: -webkit-fit-content;
  height: -moz-fit-content;
  height: calc(100% - 120px);
  min-width: 250px;
  margin: 15px;
  width: calc(100% - 80px);
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
  border-radius: 6px;
  filter: drop-shadow(0px 4px 4px rgba(221, 221, 221, 0.25));
  padding: 25px;
}
/* .addBtn {
    width: 50px;
    height: 50px;
    background: #1f7a8c;
    border-radius: 6px;
    display: flex;
    justify-content: center;
    align-items: center;
    text-decoration: none;
    color: #e6e6e6;
    font-weight: 400;
    font-size: 30px;
    line-height: 50px;
    border: 2px solid transparent;
 } */
/* .addBtn:hover {
    cursor: pointer;
    background-color: #FFFFFF;
    color:  #1F7A8C;
    font-weight: 500;
    border: 2px solid  #1F7A8C;
 }

li {
    background-color: #E1E5F2;
    padding: 15px;
    margin: 10px;
}
.title-add {
    display: flex;
    width: 150px;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 15px;
}
.title-add > h1 {
    margin-block-start: 0;
} */
</style>