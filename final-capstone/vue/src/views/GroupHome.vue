<template>
  <div class="groups" v-if="$store.state.group">
    <div class="title-add">
      <h1>{{ $store.state.group.name }}</h1>
      <span class="group-code tooltip" @click="(e) => copyToClip(e)">
         <span class="invite-code">{{this.$store.state.group.interleaveInvite}}</span>
        <span class="tool-tip-text">Copy to clipboard</span>
      </span>
      <p>{{ $store.state.group.groupDescription }}</p>

    <hr />
      <router-link
        class="addBtn new-group-button"
        v-bind:to="{ name: 'new-list' }"
        v-if="$store.state.token != ''"
        >Add</router-link
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
    copyToClip(e) {
      navigator.clipboard.writeText(e.target.textContent);
      
    },
     getItemsList(listId) {
      ShoppingListService.getItemList(listId).then(
        (items) => {
          this.$store.commit("SET_ITEMS", items.data);
        }
      );
    },
  },
};
</script>
<style>
li {
  text-decoration: none;
}

#lists-listed {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  width: 100%;
  padding-inline-start: 0px;
}

.add-button {
  font-size: 20px;
  font-weight: 450 !important;
  font-size: 20px !important;
}

.tooltip {
  position: relative;
}

.tooltip .tool-tip-text {
  visibility: hidden;
  width: 100px;
  opacity: 0;
  background-color:#e1e5f273;
  font-weight: 375;
  font-size: 14px;
  letter-spacing: 0 !important;
  text-align: center;
  border-radius: 4px;
  padding: 5px 0;
  position: absolute;
  z-index: 1;
  top: -5px;
  left: 105%;
  transition: all 250ms cubic-bezier(0.23, 1, 0.32, 1);
}

.tooltip:hover .tool-tip-text {
  visibility: visible;
  opacity: 1;
}

.group-code {
  font-weight: 600;
  margin-block-start: 0;
  letter-spacing: 2px;
}
.group-code:hover {
  cursor: pointer;
}

.invite-code:hover {
  color:#1f7a8c ;
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

.addBtn {
  width: 50px;
  height: 50px;
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
</style>