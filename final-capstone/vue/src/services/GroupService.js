import axios from 'axios';

export default {

    list() {
        
        return axios.get('/groups')
    },
    createGroup(group) { 
        return axios.post('/groups', group)
    //console.log(group)
    }

}
