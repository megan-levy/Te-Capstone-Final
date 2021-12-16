import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import ShoppingListService from '../services/ShoppingListService';
import GroupService from '../services/GroupService';
import ItemService from '../services/ItemService';

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
      state.group = Object.assign({}, state.group, group);
    },
    SET_LISTS(state, lists) {
      state.lists = lists;
    },
    SET_LIST(state, list) {
        state.list = Object.assign({}, state.list, list);
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
      ShoppingListService.getShoppingList(listId).then(list => { commit('SET_LIST', list.data) });
    },
    GET_LISTS({ commit }, groupId) {
      ShoppingListService.list(groupId).then(lists => {
        commit('SET_LISTS', lists.data)
    });
    },
    UPDATE_LIST({state}) {
      ShoppingListService.updateListInfo(state.list, state.list.listId);
    },
    POST_LIST({state}) {
      ShoppingListService.createShoppingList(state.list, state.list.listId);
    },
    DELETE_LIST_ITEMS({state}, listId) {
      ItemService.deleteAllItemsFromList(listId).then( () => {
        this.dispatch('GET_ITEMS', listId);
        this.dispatch('GET_LISTS', state.group.groupId);
      })
    },


    GET_GROUP({ commit }, groupId) {
      // ShoppingListService.getShoppingList(groupId).then(group => commit('SET_GROUP', group.data));
      GroupService.getSingle(groupId).then(group => commit('SET_GROUP', group.data));
    },
    GET_GROUPS({ commit }) {
      // ShoppingListService.list(groupId).then(lists => commit('SET_LISTS', lists.data));
      GroupService.listByUserId().then(groups => commit('SET_GROUPS', groups.data));
    },
    // UPDATE_GROUP({state}) {
    //   // ShoppingListService.updateListInfo(state.list, state.list.listId).then( r => console.log(r));
    // },
    POST_GROUP({state}) {
      // ShoppingListService.createShoppingList(state.list, state.list.listId).then( r => console.log(r));
      GroupService.createGroup(state.group).then(r => console.log(r));
    },

    // GET_ITEM({ commit }, itemId) {
    //   ShoppingListService.getShoppingList(listId).then(list => { commit('SET_LIST', list.data) });
    // },
    GET_ITEMS({ commit }, listId) {
      ShoppingListService.getItemList(listId).then(items => {
        commit('SET_ITEMS', items.data)
    });
    },
    DELETE_ITEM({state}, itemId) {
      ItemService.deleteItemFromList(itemId).then( () => {
        this.dispatch('GET_ITEMS', state.list.listId);
        this.dispatch('GET_LISTS', state.group.groupId);
      })

    },
    // UPDATE_LIST({state}) {
    //   ShoppingListService.updateListInfo(state.list, state.list.listId);
    // },
    // POST_LIST({state}) {
    //   ShoppingListService.createShoppingList(state.list, state.list.listId);
    // },

  }
});
