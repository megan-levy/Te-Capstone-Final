<template>
    <div class="groups">
        <h1>Groups</h1>
        <!-- <p>You must be authenticated to see this</p> -->
        <router-link v-bind:to="{ name: 'new-group' }" v-if="$store.state.token != ''">Create New Group</router-link>

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