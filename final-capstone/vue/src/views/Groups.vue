<template>
  <div class="groups">
    <!-- <router-view /> -->
    <h1>Groups</h1>
    <br />
    <router-link
      class="addBtn"
      v-bind:to="{ name: 'new-group' }"
      v-if="$store.state.token != ''"
      >+</router-link
    >

    <hr />
    <div>
      <ul id="group-name">
        <li v-for="group in $store.state.groups" :key="group.groupId">
          <h1>{{ group.name }}</h1>
          <p>{{ group.groupDescription }}</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import GroupService from "@/services/GroupService.js";

export default {
  name: "groups",
  mounted() {
    this.listGroups();
    // console.log("howdy");
    //GroupService.list();
  },
  methods: {
    listGroups() {
      GroupService.list().then((groups) => {
        this.$store.commit("SET_GROUPS", groups.data);
      });
    },
  },
};
</script>
<style>
.groups {
  height: fit-content;
  min-width: 350px;
  margin: 15px;
  width: calc(100% - 80px);
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
  border-radius: 6px;
  filter: drop-shadow(0px 4px 4px rgba(221, 221, 221, 0.25));
  padding: 25px;
}
.addBtn {
  width: 75px;
  height: 75px;
  background: #1f7a8c;
  border-radius: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: #e6e6e6;
  font-weight: 200;
  font-size: 3.5rem;
  border: 2px solid transparent;
}
.addBtn:hover {
  cursor: pointer;
  background-color: #ffffff;
  color: #1f7a8c;
  border: 2px solid #1f7a8c;
}

li {
  background-color: #e1e5f2;
  padding: 15px;
  margin: 10px;
}
</style>