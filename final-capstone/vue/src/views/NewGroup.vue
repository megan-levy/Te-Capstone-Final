<template>
  <div id="new-group" class="text-center auth-container">
      <form class="form-new-group">
        <h1 class="h3 mb-3 font-weight-normal">Create New Group</h1>
         <hr>
         <br>
         <label for="text" class="sr-only">
             <span>Group Name:</span>
             <input
             type="text"
             id="group-name"
             class="form-control"
             placeholder="Group Name..."
             required/>
         </label>
         <label for='email' class="sr-only">
             <span>Add Email of Member:</span>
             <input 
             type='email'
             id='new-email'
             class="form-control"
             placeholder="Member Email"
             required
             />
         </label>
          <button class="btn btn-lg btn-primary btn-block" type="submit">
        Send Invites
      </button>
       <router-link tag="a" class="helper-link" :to="{ name: 'login' }">Cancel</router-link>
      </form>
  </div>
</template>

<script>
export default {

}
</script>

<style>
form {
    /* min-height: 420px; */
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
</style>