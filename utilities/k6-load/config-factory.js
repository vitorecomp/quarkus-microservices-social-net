export const getConfig = () => {
  const config = {
    maxUsers: 1000000,
    postRatio: 0.1,
    viewRatio: 10,
    likeRatio: 0.01,
    commentRatio: 0.01,
    followRatio: 0.01,

    userUrl: 'http://localhost:3000',
    postUrl: 'http://localhost:3001',
    feedUrl: 'http://localhost:3002',
  };
  return config;
}

export const shouldPost = () => {
  const config = getConfig();
  return Math.random() < config.postRatio;
}

export const shouldLike = () => {
  const config = getConfig();
  return Math.random() < config.likeRatio;
}

export const shouldComment = () => {
  const config = getConfig();
  return Math.random() < config.commentRatio;
}

export const shouldFollow = () => {
  const config = getConfig();
  return Math.random() < config.followRatio;
}
