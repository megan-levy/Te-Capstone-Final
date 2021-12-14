import axios from 'axios';

export default {

    //for joining group with valid id
    joinGroup(code, groupId) {
        return axios.post(`/members/${groupId}`, code, { params: { "groupId": groupId }});
    },
    //for joining group with valid id
    leaveGroup(groupId) {
        return axios.delete(`/members/${groupId}`, { params: { "groupId": groupId }});
    },
}