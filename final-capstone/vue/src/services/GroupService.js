import axios from 'axios';

export default {

    list() { 
        return axios.get('/groups')
    },
    createGroup(group) { 
        return axios.post('/groups', group);
    },
    getSingle(groupId) {
        return axios.get(`/groups/${groupId}`, { params: { "groupId": groupId } });
    }

    //for joining group with valid id

}
