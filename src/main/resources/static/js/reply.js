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