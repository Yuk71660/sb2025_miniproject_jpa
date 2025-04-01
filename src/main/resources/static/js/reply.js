async function getReplies({bno, page, size}) {
    console.log("bno : " + bno);
    console.log("page : " + page);
    console.log("size : " + size);

    let response = await axios.get(`/replies/list/${bno}`, {params: {page, size}});

    return response.data;
}

async function addReply(replyDTO) {
    let response = await axios.post('/replies/', replyDTO);
    return response;
}

async function removeReply(rno) {
    let response = await axios.delete(`/replies/${rno}`);
    return response;
}


async function modifyReply(rno, replyText) {
    console.log("rno : " + rno);
    console.log("replyText : " + replyText);
    let response = await axios.put(`/replies/${rno}`, replyText, {headers: {'Content-Type': 'text/plain'}});
    return response;
}
