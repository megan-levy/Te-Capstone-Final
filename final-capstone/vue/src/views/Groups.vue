<template>
    <div class="groups">
        <h1>Groups</h1>
        <!-- <p>You must be authenticated to see this</p> -->
        <div class="link-to-group">
        <router-link v-bind:to="{ name: 'new-group' }" v-if="$store.state.token != ''">Create New Group</router-link>
        </div>
       <div class="current-groups">
        <ul id="example-1">
            <li v-for="group in $store.state.groups" :key="group.groupId">
                <h1>{{group.name}}</h1>
                <p>{{group.description}}</p>
            </li>
        </ul>
        </div>
    </div>
</template>

<script>
    import GroupService from '@/services/GroupService.js';

    export default {
    name: "groups",
    mounted() {
        this.listGroups();
        // console.log("howdy");
        // GroupService.list();
    },
    methods: {
            listGroups() {
                let groups = GroupService.list();
                this.$store.commit("SET_GROUPS", (groups.data));
            }
        }
    };
</script>
<style>
 .groups {
  height: fit-content;
    min-width: 350px;
    width: fit-content;
    display: flex;
    flex-direction: column;
    background-color: #ffffff;
    border-radius: 6px;
    filter: drop-shadow(0px 4px 4px rgba(221, 221, 221, 0.25));
    padding: 25px;
 }
 .current-groups {
     
 }
</style>