<template>
    <ul class="vertical-list" id='lists-listed'>
        <p>{{ greeting }}</p>
        <!-- <router-link 
            tag="a" 
            class="group-list-item" 
            v-bind:to="{ name: 'shopping-list', params: { listId: `${list.listId}` }}"  
            v-for="list in $store.state.lists" 
            :key="list.listId"
        >
            <h1>{{list.listName}}</h1>
            <p>{{list.listDescription}}</p>
            <p>Item quantity</p>
        </router-link> -->
    </ul>
</template>

<script>

    // let title = this.$props.title ? this.$props.title : "placeholder";

    export default {
        props: ['title'],
        data() {
            return {
                title: this.$props.title,
                description: this.$props.description,
                date: this.$props.date
            }
        }
    }
</script>
<style scoped>
    p {
        font-size: 2em;
        text-align: center;
    }
</style>