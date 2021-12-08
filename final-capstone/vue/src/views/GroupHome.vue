<template>

    <div class="groups">
       
         <!-- <router-view /> -->
         <div class="title-add">
            <h1>Group</h1>
            <router-link class="addBtn" v-bind:to="{ name: 'new-group' }" v-if="$store.state.token != ''">+</router-link>
         </div>
        
        <hr/>
        <div>
        <ul id="group-name">
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
 /* .groups { */
    /* height: fit-content;
    min-width: 350px;
    margin: 15px;
    width: calc(100% - 80px);
    display: flex;
    flex-direction: column;
    background-color: #ffffff;
    border-radius: 6px;
    filter: drop-shadow(0px 4px 4px rgba(221, 221, 221, 0.25));
    padding: 25px;
 } */
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