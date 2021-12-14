<template>
  <div class="groups">
    <h1>Groups</h1>
    <br />
    <div class="buttons-groups">
      <router-link
        class="addBtn new-group-button"
        v-bind:to="{ name: 'new-group' }"
        v-if="$store.state.token != ''"
        >Add</router-link
      >
      <a
        class="addBtn new-group-button"
        v-on:click="toggleJoin = !toggleJoin"
        v-if="$store.state.token != ''"
        >Join</a
      >
      <div v-if="toggleJoin" class="join-modal">
        <groups-toggle v-model="toggleJoin" />
      </div>
    </div>
    <br>
    <hr />
    <div class="items-container">
      <ul
        class="vertical-list"
        v-if="$store.state.token != ''"
        id="groups-listed"
      >
        <list-card
          v-for="group in $store.state.groups"
          :key="group.groupId"
          v-bind:id="group.groupId"
          v-bind:type="'group'"
          v-bind:title="group.name"
          v-bind:description="group.groupDescription"
          v-bind:date="group.createdOn"
        />
      </ul>
    </div>
  </div>
</template>

<script>
import GroupService from "@/services/GroupService.js";
import ListCard from "../components/ui/ListCard.vue";
import JoinGroup from "../components/ui/JoinGroup.vue";

export default {
  name: "groups",
  components: {
    ListCard,
    groupsToggle: JoinGroup,
  },
  mounted() {
    this.listGroups();
  },
  methods: {
    listGroups() {
      GroupService.listByUserId().then((groups) => {

        this.$store.commit("SET_GROUPS", groups.data);
      });
    },
  },
  data() {
    return {
      toggleJoin: false,
    };
  },
};
</script>
<style>
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

.date {
  font-weight: 400;
  font-size: 16px;
}

#groups-listed {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  width: 100%;
  padding-inline-start: 0px;
}

.join-modal {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #12121268;
  top: 0;
  left: 0;
  border-radius: 6px;
}
.new-group-button {
  font-size: 20px;
  font-weight: 450 !important;
}
.buttons-groups {
  display: flex;
  width: calc(175px + 1rem);
  justify-content: space-between;
}
.items-container {
  overflow-y: scroll;
  height: 100%;
}

@media only screen and (max-width: 600px) {
  #groups-listed {
    display: flex;
    width: 100%;
    flex-direction: column;
    -webkit-padding-start: 0px;
    padding-inline-start: 0px;
  }
  .groups {
    height: -webkit-fit-content;
    height: -moz-fit-content;
    height: calc(100% - 95px);
    width: calc(100% - 60px);
    margin: 20px;
    min-width: 250px;
    background-color: #ffffff;
    border-radius: 6px;
    filter: drop-shadow(0px 4px 4px rgba(221, 221, 221, 0.25));
    padding: 10px;
  }
}
</style>