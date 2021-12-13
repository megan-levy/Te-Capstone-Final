import Vue from 'vue'
import Router from 'vue-router'
// import Home from '../views/Home.vue'
import NewList from '../views/NewList.vue'
import GroupHome from '../views/GroupHome.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import NewGroup from '../views/NewGroup.vue'
import Groups from '../views/Groups.vue'
import ShoppingList from '../views/ShoppingList.vue'
import NewItem from '../views/NewItem.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/', //this will need to be /:userId in order to show only groups for that user
      name: 'groups',
      component: Groups,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/groups/:groupId/lists",
      name: 'group-home',
      component: GroupHome,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/new-group",
      name: "new-group",
      component: NewGroup,
      meta: {
        requiresAuth: false
      }
    }, 
    {
      path: "/new-list",
      name: "new-list",
      component: NewList,
      meta: {
        requiresAuth: false
      }
    }, 
    {
      path: "/groups/:groupId/lists/:listId",
      name: 'shopping-list',
      component: ShoppingList,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/groups/:groupId/lists/:listId/new-item",
      name: "new-item",
      component: NewItem,
      meta:{
        requiresAuth: false
      }
   }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
