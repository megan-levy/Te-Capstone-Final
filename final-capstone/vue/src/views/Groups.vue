<template>

    <div class="groups">
       
         <!-- <router-view /> -->
        <h1>Groups</h1>
        <br>
         <router-link class="addBtn" v-bind:to="{ name: 'new-group' }" v-if="$store.state.token != ''">+</router-link>
        <ul id="example-1">
            <li v-for="group in $store.state.groups" :key="group.groupId">
                <h1>{{group.name}}</h1>
                <p>{{group.description}}</p>
            </li>
        </ul>
    </div>
</template>

<script>
    import GroupService from '@/services/GroupService.js';

    export default {
    name: "groups",
    mounted() {
        this.listGroups();
        // console.log("howdy");
        GroupService.list();
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
 }
</style>