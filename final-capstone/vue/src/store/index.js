import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import ShoppingListService from '../services/ShoppingListService';

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token');
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    groups: [],
    group: {},
    shoppingLists: [],
    lists: [],
    list: {},
    items: [],
    listId: 0,
    item: {},
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    SET_GROUPS(state, groups) {
      state.groups = groups;
    },
    SET_GROUP(state, group) {
      state.group = group;
    },
    SET_LISTS(state, lists) {
      state.lists = lists;
    },
    SET_LIST(state, list) {
      state.list = list;
    },
    SET_ITEMS(state, items) {
      state.items = items;
    },
    SET_TOTAL_ITEM_COUNT(state, item_count) {
      state.item_count = item_count;
    },
    SET_LIST_ID(state, listId) {
      state.listId = listId;
    },
    SET_ITEM(state, item) {
      state.item = item;
    },

    
    
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    }
  },
  actions: {
    GET_LIST({ commit }, listId) {
      ShoppingListService.getShoppingList(listId).then(list => commit('SET_LIST', list));
    },
    GET_LISTS({ commit }, groupId) {
      ShoppingListService.list(groupId).then(lists => commit('SET_LISTS', lists.data));

      // ShoppingListService.list(this.$route.params.groupId).then((lists) => {
      //   console.log(lists);
      //   this.$store.commit("SET_LISTS", lists.data);
      // });
    }
  }
});
